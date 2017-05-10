import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class WordGuess {
	static String Word="",sscore,guess="",wordOut,preGuesses=" ";
	static int high1=0, high2=0, high3=0;
	static String highone="No One", hightwo="No One", highthree="No One";
	static double time=0.0;
	static int numz=0;
	public static void main(String [ ] args){
		title();
		intro();
		menue();
		
	} 

	private static void intro() {
		System.out.println("Welcome to the Guessing game!");
		
	}

	private static void menue() {
		
		Scanner hap=new Scanner(System.in);
		System.out.println("Press 1 to play");
		System.out.println("Press 2 for instructions");
		System.out.println("Press 3 for high scores");
		int one=1;
		try{
			one=hap.nextInt();
		}catch(Exception e){
			System.out.println("Starting game!");
		}
		System.out.println("********************************");
		switch(one){
		case 1: game();break;
		case 2: instructions();break;
		case 3: scores();break;
		}
	}

	private static void scores() {
		Scanner hap=new Scanner(System.in);
		System.out.println(filerecall());
		// TODO Auto-generated method stub
		
		System.out.println("Enter 1 and hit enter to go back to the menu");
		int one=hap.nextInt();
		System.out.println("********************************");
		menue();
	}

	private static void instructions() {
		Scanner hap=new Scanner(System.in);
		System.out.println("Type a letter you want to guess and press enter");
		System.out.println("Type \"?\" and press enter to try and guess the whole word");
		System.out.println("Be quick though, time does affect your final score");
		System.out.println("Press \"!\" to quit");
		System.out.println("Goodluck!");
		System.out.println("Enter 1 and hit enter to go back to the menu");
		int one=hap.nextInt();
		menue();
		
	}

	private static void game() {
		preGuesses=" ";
		numz = 0;
		time=0;
		sscore="0";
		Word="";
		Scanner hap=new Scanner(System.in);
		Word=getRandomWord();
		startWord();
		//System.out.println(Word);
		System.out.print("Your word is: ");
		System.out.println(wordOut);
		while(!wordOut.equals(Word)&&!guess.equals("!")){
			System.out.println("The word so far is: "+wordOut);
			System.out.println("You guesses so far:"+preGuesses.substring(0,preGuesses.length()-1));
			System.out.print("Guess: ");
			double then=System.currentTimeMillis();
			guess=hap.nextLine();
			time+=System.currentTimeMillis()-then;
			
			if(guess.equals("panda")){
				numz=1;
				time=1000;
				wordOut=Word;
			}
			if(guess.equals("!")){
				time=0;
				menue();
			}
			if(!guess.equals("?")){
				guess(guess);
				guesses();
			}else{
				wordGuess();
			}
			
		}
		win();
		System.out.println(wordOut);
		
	}

	private static void guesses() {
		boolean g=false;
		int a;
		for(a=0;a<preGuesses.length();a++){
			if(preGuesses.substring(a,a+1).equals(guess)){
				g=true;
		}}
		if(g){
			System.out.println("You have already guessed that!");
		}else{
			numz++;
			preGuesses=preGuesses+guess+",";
		}
		// TODO Auto-generated method stub
		
	}

	private static void wordGuess() {
		Scanner hap=new Scanner(System.in);
		System.out.print("Enter you guess: ");
		guess=hap.nextLine();
		System.out.println("");
		if(guess.equals(Word)){
			wordOut=guess;;
		}else{
			System.out.println("Sorry that is incorrect");
		}
		// TODO Auto-generated method stub
		
	}

	private static void win() {
		Scanner hap=new Scanner(System.in);
		String name;
		System.out.println("Win");
		System.out.println("Total time was :"+time+" milliseconds using "+numz+" guesses!");
		time=time/1000;
		System.out.println("Your final score was: "+calcScore());
		System.out.println("Please enter your name: ");
		name=hap.nextLine();
		highscore((int)calcScore(),name);
		System.out.println("Enter 1 if you would like to play again:");
		String temp =hap.nextLine();
		if(temp.equals("1")){
			game();
		}else
			System.out.println("Thanks for playing!");
		
	}

	private static double calcScore() {
		double score=0;
		//score=(((double)((Word.length())*20)/numz)+(2*(((Word.length())*16)/time)))*1.2;
		score=(double)(((Word.length()+3)*22/numz));
		System.out.println("First:"+score+"  Second:"+((Word.length()+3)*16/time));
		score+=(double)(((Word.length()+3)*14/time));
		score*=2.8;
		score+=.5;
		score=(int)score;
		return score;
	}

	private static String getRandomWord() {
		String temp="";
		int num=0,ran=0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("wordList.txt"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				temp=temp+sCurrentLine;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int a=0;a<temp.length();a++){
			if(temp.substring(a,a+1).equals(",")){
				num++;
			}
		}		
		ran=(int)((num-1)*Math.random());
		num=0;
		for(int a=0;a<temp.length();a++){
			if(temp.substring(a,a+1).equals(",")){
				num++;
				if(num==ran){
					for(int i=a+1;!(temp.substring(i,i+1).equals(","));i++){
						Word+=temp.substring(i,i+1);	
					}
					return Word;	
				}
			}
		}
		return Word;
	}

	private static void title() {
		
		System.out.println("*   *   *  **   ***   **      ***   *   *  ****    ***   ***");
		System.out.println("*  * *  * *  *  *  *  * *    *      *   *  *      *     *");
		System.out.println(" * * * *  *  *  * *   *  *   * **   *   *  ****    ***   ***");
		System.out.println(" * * * *  *  *  *  *  * *    *   *  *   *  *          *     *");
		System.out.println("  *   *    **   *  *  **      ***     **   ****    ***   ***");
	}
	
	//Reads high score file
	private static String filerecall() {
		sscore="";
		try {
			BufferedReader br = new BufferedReader(new FileReader("WordGuessScores.txt"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				sscore=sscore+sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sscore.substring(0,sscore.length()); 	
	}
	//Saves high scores
	private static void filesave(String highone2, int high12, String hightwo2,
			int high22, String highthree2, int high32) {
		  try {
	            String text1 = highone2+"-"+high12+"!"+hightwo2+"-"+high22+"!"+highthree2+"-"+high32+"!";
	           // System.out.println(text1);
	            BufferedWriter out = new BufferedWriter(new FileWriter("WordGuessScores.txt"));
	            out.write(text1);
	            out.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("Exception ");       
	        }
	}
	private static String startWord(){
		wordOut="";
		for(int a=0; a<Word.length();a++)
			wordOut+="-";
		return wordOut;
	}
	private static void guess(String guess){
		for(int a=0;a<Word.length();a++){
			if (guess.equals(Word.substring(a, a+1))){
				wordOut=wordOut.substring(0,a)+guess+wordOut.substring(a+1);
			}
		}
	}
	//*************
	
	
	
	
	private static void highscore(int score,String name) {
		sscore=filerecall();
		int indx,indxtwo,Q;
		String numX;
		indx=sscore.indexOf("!");
		indxtwo=sscore.indexOf("-");
		numX=sscore.substring(indxtwo, indx);
		high1=changeTonum(numX);
		highone=sscore.substring(0,indxtwo);
		sscore=sscore.substring(indx+1);
		indx=sscore.indexOf("!");
		indxtwo=sscore.indexOf("-");
		numX=sscore.substring(indxtwo, indx);
		high2=changeTonum(numX);
		hightwo=sscore.substring(0,indxtwo);
		sscore=sscore.substring(indx+1);
		indx=sscore.indexOf("!");
		indxtwo=sscore.indexOf("-");
		numX=sscore.substring(indxtwo,indx);
		high3=changeTonum(numX);
		highthree=sscore.substring(0,indxtwo);
		sscore=sscore.substring(indx+1);
		System.out.println(sscore);
		if(score>high1){
			System.out.print("You have the top high score!");
			highthree=hightwo;
			hightwo=highone;
			highone=name;                  
			highone=highone.toUpperCase();
			high3=high2;
			high2=high1;
			high1=score;	
		}else if(score>high2){
			highthree=hightwo;
			System.out.print("You have the 2nd top high score!");
			hightwo=name;
			hightwo=hightwo.toUpperCase();
			hightwo=hightwo;
			high3=high2;
			high2=score;
		}else if(score>high3){
			highthree=hightwo;
			System.out.print("You have the 3rd top high score!");
			highthree=name;
			highthree=highthree.toUpperCase();
			high3=score;
		}else{
			System.out.print("You do not have a high score.");
		}
			System.out.println("\n High Scores: \n 1: "+highone+"---"+high1+"\n 2: "+hightwo+"---"+high2+"\n 3: "+highthree+"---"+high3);
		
			filesave(highone,high1,hightwo,high2,highthree,high3);
		}

	private static int changeTonum(String numX) {
		int len, total=0, num=0;
		String up;
		len=numX.length();
		for(int i=0; i<len;i++){
			total=total*10;
			up=numX.substring(i, i+1);
			if(up.equals("0")){
				num=0;
			}
			else if(up.equals("1")){
				num=1;
			}
			else if(up.equals("2")){
				num=2;
			}
			else if(up.equals("3")){
				num=3;
			}
			else if(up.equals("4")){
				num=4;
			}
			else if(up.equals("5")){
				num=5;
			}
			else if(up.equals("6")){
				num=6;
			}
			else if(up.equals("7")){
				num=7;
			}
			else if(up.equals("8")){
				num=8;
			}
			else if(up.equals("9")){
				num=9;
			}else
				num=0;
			total=total+num;
		}
		if(total>20000000)
			total=0;
		return(total);
		
	}
	

}
