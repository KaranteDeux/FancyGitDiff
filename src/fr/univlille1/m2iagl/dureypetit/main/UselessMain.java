package fr.univlille1.m2iagl.dureypetit.main;

import java.util.List;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;
import fr.univlille1.m2iagl.dureypetit.model.Commit;

public class UselessMain {
	
	public static void main(String [] args){
		GitRepository gitRepo = new GitRepository("../CauseEffectChain/");
		
		/*
		File file = new File(".git");
		File[] files = file.listFiles();
		
		System.out.println(Arrays.toString(files));
		*/
		List<Commit> commits = gitRepo.getCommitsList();
		
		System.out.println(commits);
	}

}
