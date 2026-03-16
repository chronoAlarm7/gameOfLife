import java.util.concurrent.TimeUnit;

public class main{
	static int[][] life = new int[50][200];

	public static void main(String[] args){
		makeLife();
		clearScreen();
		printLife();
		//for(int i=0; i < 20; i++){
		while (true){
			int counter = 0;
			makeLife();
			for(int i=0; i < 40; i++){
				playLife(1);
				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				clearScreen();
				printLife();
			}
		}
	}
	
	public static void makeLife(){
		for(int i=0; i < life.length; i++){
			for(int x=0; x < life[i].length; x++){
				double rand = Math.random() * 10;
				if (rand > 8) {
					life[i][x] = 1;
				} else{
					life[i][x] = 0;
				}
			}
		}
	}

	public static void printLife(){
		System.out.print("\n");
		for(int i=0; i < life.length; i++){
			System.out.print("\n");
			for(int x=0; x < life[i].length; x++){
				if(life[i][x] == 1){
					System.out.print("■");
				} else {
					System.out.print("▫");
				}
			}
		}
	}

	public static void playLife(int iterations){
		for(int i=0; i < iterations; i++){
			int[][] newLife = new int[life.length][life[0].length];

			for(int r=0; r < life.length; r++){
				for(int c=0; c < life[0].length; c++){

					int neighbors = checkNeighbors(r, c);

					if(life[r][c] == 1){
						if(neighbors < 2 || neighbors > 3){
							newLife[r][c] = 0;
						}
						if(neighbors == 2 || neighbors == 3){
							newLife[r][c] = 1;
						}
					} else {
						if(neighbors == 3){
							newLife[r][c] = 1;
						}
					}
				}
			} life = newLife;
		}
	}

	public static void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	//im a chud
	public static int checkNeighbors(int r, int c){
		int neighbors = 0;

		//check the row above
		if(r > 0){
			if(c > 0){
				if(life[r-1][c-1] == 1){
					neighbors++;
				}
			}
			if(life[r-1][c] == 1){
				neighbors++;
			}
			if(c != life[0].length - 1){
				if(life[r-1][c+1] == 1){
					neighbors++;
				}
			}
		}
		//check row its on
		if(c > 0){
			if(life[r][c-1] == 1){
				neighbors++;
			}
		}
		if(c != life[0].length - 1){
			if(life[r][c+1] == 1){
				neighbors++;
			}
		}
		//bottom row
		if(c > 0){
			if(r != life.length - 1){
				if(life[r+1][c-1] == 1){
					neighbors++;
				}
			}
		}
		if(r != life.length - 1){
			if(life[r+1][c] == 1){
				neighbors++;
			}
			if(c != life[0].length - 1){
				if(life[r+1][c+1] == 1){
					neighbors++;
				}
			}
		}
		return neighbors;
	}
}

