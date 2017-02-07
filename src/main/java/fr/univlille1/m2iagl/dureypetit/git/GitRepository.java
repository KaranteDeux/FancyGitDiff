package fr.univlille1.m2iagl.dureypetit.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.Commit;
import fr.univlille1.m2iagl.dureypetit.parser.ClassVisitor;

public class GitRepository {


	public static ClassModel classModel;

	private Repository repository;
	private String folderPath;
	private List<Commit> commits;
	
	
	public GitRepository(String folderPath){
		this.folderPath = folderPath;

		try {
			repository = new FileRepositoryBuilder()
					.setGitDir(new File(this.folderPath + ".git"))
					.build();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public List<Commit> getCommitsList(){
		return commits;
	}

	private List<Commit> constructCommitsList(){
		Git git = new Git(repository);
		Iterable<RevCommit> revCommits = null;

		try {
			revCommits = git.log().all().call();
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		List<Commit> commits = new ArrayList<>();

		for (RevCommit revCommit : revCommits) {
			commits.add(new Commit(revCommit.getId(), revCommit.getAuthorIdent().getName(), revCommit.getAuthorIdent().getWhen(), revCommit.getFullMessage(), readElementsAt(repository, revCommit.getId(), "")));
		}
		
		git.close();

		return commits;
	}

	public void constructModelForEachCommit(){
		commits = constructCommitsList();

		for(Commit commit : commits){
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
					commit.addClassModel(fileChanged, GitRepository.classModel);

				} catch(Exception e){

				}
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
}
