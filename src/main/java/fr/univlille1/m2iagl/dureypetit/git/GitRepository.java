package fr.univlille1.m2iagl.dureypetit.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.Side;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.CommitModel;
import fr.univlille1.m2iagl.dureypetit.parser.ClassVisitor;

public class GitRepository {


	public static ClassModel classModel;

	private Repository repository;
	private String folderPath;
	private List<CommitModel> commits;


	public GitRepository(String folderPath){
		this.folderPath = folderPath;

		try {
			repository = new FileRepositoryBuilder()
					.setGitDir(new File(this.folderPath + ".git"))
					.build();
		} catch (IOException e) {

			e.printStackTrace();
		}

		constructCommitsList();
	}

	public List<CommitModel> getCommitsList(){
		return commits;
	}

	private List<CommitModel> constructCommitsList(){
		Git git = new Git(repository);
		Iterable<RevCommit> revCommits = null;

		try {
			revCommits = git.log().all().call();
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		List<RevCommit> revCommitsList = reverseCommitsOrder(revCommits);

		commits = new ArrayList<>();

		int i=0;

		ObjectId oldCommitId = null;
		for (RevCommit revCommit : revCommitsList) {
			ObjectId commitId = revCommit.getId();

			String author = revCommit.getAuthorIdent().getName();
			Date date = revCommit.getAuthorIdent().getWhen();
			String fullMessage = revCommit.getFullMessage();
			
			CommitModel commitModel = null;
			if(i==0)
				commitModel = new CommitModel(commitId, author, date, fullMessage, readElementsAt(repository, commitId, ""));
			else
				commitModel = new CommitModel(commitId, author, date, fullMessage, readElementsChangedAt(repository, commitId, oldCommitId));
			
			updateClassModel(commitModel);
			
			commits.add(commitModel);
			i++;
			oldCommitId = commitId;
		}

		git.close();

		return commits;
	}

	private List<RevCommit> reverseCommitsOrder(Iterable<RevCommit> revCommits){
		List<RevCommit> commits = new ArrayList<RevCommit>();

		for(RevCommit revCommit : revCommits){
			commits.add(0, revCommit);
		}
		return commits;
	}

	public void updateClassModel(CommitModel commit){
		for(String fileChanged : commit.getFilesChanged()){

			try {
				String contentFile = getContentFile(commit.getId(), fileChanged);

				FileWriter file = new FileWriter("/tmp/tmpFile.java");

				file.write(contentFile);
				file.close();

				FileInputStream in = new FileInputStream("/tmp/tmpFile.java");
				CompilationUnit cu = JavaParser.parse(in);

				// prints the resulting compilation unit to default system output
				new ClassVisitor().visit(cu, null);
				commit.addClassModel(GitRepository.classModel);

			} catch(Exception e){

			}
		}
	}

	private String getContentFile(ObjectId commitId, String filename){
		try (RevWalk revWalk = new RevWalk(repository)) {
			RevCommit commit = revWalk.parseCommit(commitId);
			// and using commit's tree find the path
			RevTree tree = commit.getTree();

			// now try to find a specific file
			try (TreeWalk treeWalk = new TreeWalk(repository)) {
				treeWalk.addTree(tree);
				treeWalk.setRecursive(true);
				treeWalk.setFilter(PathFilter.create(filename));
				if (!treeWalk.next()) {
					throw new IllegalStateException("Did not find expected file '" + filename +  "'");
				}

				ObjectId objectId = treeWalk.getObjectId(0);
				ObjectLoader loader = repository.open(objectId);

				// and then one can the loader to read the file
				return new String(loader.getBytes());
			}

		} catch(Exception e){
			return "";
		}
	}


	private List<String> readElementsAt(Repository repository, AnyObjectId commitId, String path) {

		try {
			RevWalk revWalk = new RevWalk(repository);
			RevCommit commit = revWalk.parseCommit(commitId);

			RevTree tree= commit.getTree();
			TreeWalk treeWalk = new TreeWalk(repository);
			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);

			List<String> elementsChanged = new ArrayList<>();
			while(treeWalk.next()){
				elementsChanged.add(treeWalk.getPathString());
			}

			revWalk.close();
			treeWalk.close();

			return elementsChanged;

		} catch(Exception e){
			return new ArrayList<>();
		}
	}

	private List<String> readElementsChangedAt(Repository repository, ObjectId commitId, ObjectId oldCommitId) {
		List<String> elementsChanged = new ArrayList<String>();

		// prepare the two iterators to compute the diff between
		try {
			ObjectReader reader = repository.newObjectReader();

			RevWalk walk = new RevWalk(repository);
			RevTree tree = walk.parseTree(commitId);
			RevTree oldTree = walk.parseTree(oldCommitId);

			CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
			ObjectReader oldReader = repository.newObjectReader();
			oldTreeParser.reset(oldReader, tree.getId());

			CanonicalTreeParser newTreeParser = new CanonicalTreeParser();
			ObjectReader newReader = repository.newObjectReader();
			newTreeParser.reset(newReader, oldTree.getId());

			walk.dispose();

			Git git = new Git(repository);
			List<DiffEntry> diffs= git.diff()
					.setNewTree(newTreeParser)
					.setOldTree(oldTreeParser)
					.call();
			for (DiffEntry entry : diffs) {
				String path = entry.getPath(Side.NEW);
				if(path.equals("/dev/null")){
					path = entry.getPath(Side.OLD);
				}
				elementsChanged.add(path);
			}			


			git.close();
			return elementsChanged;


		} catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}
}
