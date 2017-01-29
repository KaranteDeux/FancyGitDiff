package fr.univlille1.m2iagl.dureypetit.git;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import fr.univlille1.m2iagl.dureypetit.helper.ProcessHelper;
import fr.univlille1.m2iagl.dureypetit.model.Commit;

public class GitRepository {


	Repository repository;
	String folderPath;

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
		Git git = new Git(repository);
		Iterable<RevCommit> commits = null;

		try {
			commits = git.log().all().call();
		} catch (GitAPIException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;

		List<Commit> commitsId = new ArrayList<>();
		
		for (RevCommit commit : commits) {
			commitsId.add(new Commit(commit.getId().name(), commit.getAuthorIdent().getName(), commit.getAuthorIdent().getWhen(), commit.getFullMessage(), readElementsAt(repository, commit.getId().name(), "")));
		}

		return commitsId;
	}
	
	public void constructModelForEachCommit(){
		List<Commit> commits = getCommitsList();
		
		for(Commit commit : commits){
			try {
				ProcessHelper.resetHard(folderPath + "src", commit.getId());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private List<String> readElementsAt(Repository repository, String commit, String path) {
		List<String> items = new ArrayList<>();
		try {
			RevCommit revCommit = buildRevCommit(repository, commit);

			// and using commit's tree find the path
			RevTree tree = revCommit.getTree();
			//System.out.println("Having tree: " + tree + " for commit " + commit);

			

			// shortcut for root-path
			if(path.isEmpty()) {
				try (TreeWalk treeWalk = new TreeWalk(repository)) {
					treeWalk.addTree(tree);
					treeWalk.setRecursive(false);
					treeWalk.setPostOrderTraversal(false);

					while(treeWalk.next()) {
						items.add(treeWalk.getPathString());
					}
				}
			} else {
				// now try to find a specific file
				try (TreeWalk treeWalk = buildTreeWalk(repository, tree, path)) {
					if((treeWalk.getFileMode(0).getBits() & FileMode.TYPE_TREE) == 0) {
						throw new IllegalStateException("Tried to read the elements of a non-tree for commit '" + commit + "' and path '" + path + "', had filemode " + treeWalk.getFileMode(0).getBits());
					}

					try (TreeWalk dirWalk = new TreeWalk(repository)) {
						dirWalk.addTree(treeWalk.getObjectId(0));
						dirWalk.setRecursive(false);
						while(dirWalk.next()) {
							items.add(dirWalk.getPathString());
						}
					}
				}
			}
		} catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}

		return items;
	}

	private static RevCommit buildRevCommit(Repository repository, String commit) throws IOException {
		// a RevWalk allows to walk over commits based on some filtering that is defined
		try (RevWalk revWalk = new RevWalk(repository)) {
			return revWalk.parseCommit(ObjectId.fromString(commit));
		}
	}

	private static TreeWalk buildTreeWalk(Repository repository, RevTree tree, final String path) throws IOException {
		TreeWalk treeWalk = TreeWalk.forPath(repository, path, tree);

		if(treeWalk == null) {
			throw new FileNotFoundException("Did not find expected file '" + path + "' in tree '" + tree.getName() + "'");
		}

		return treeWalk;
	}
}
