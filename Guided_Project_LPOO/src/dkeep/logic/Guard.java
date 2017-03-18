package dkeep.logic;
  
public class Guard implements Character{

	private char GuardSprite;
	private int Guard_i; 
	private int Guard_j;
	private int ranGuard; 
	
	private final int[]movementGuard_i={0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,-1,-1,-1,-1};
	private final int[]movementGuard_j={-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};	
	private int GuardIterator;


	public Guard(){
		this.Guard_i=-4;
		this.Guard_j=-4;
	}

	
	//random guard
	public Guard(int i,int j, char s, int random){

		this.Guard_i=i;
		this.Guard_j=j;
		this.GuardSprite=s;
		this.GuardIterator=0;
		this.ranGuard = random;
		}


	public int getCoordenateI(){return Guard_i;}

	public int getCoordenateJ(){return Guard_j;}

	public char getSprite(){return GuardSprite;}

	public void setCoordenateI(int i){this.Guard_i = i;}

	public void setCoordenateJ(int j){this.Guard_j = j;}

	public int getStun(){return 0;}
	public void setStun(int x) {} {}
	
	public void move(){
		
		//rookie /normal
		if (ranGuard == 0)
		{
			if(GuardIterator == 24)
				GuardIterator = 0;

			Guard_i += movementGuard_i[GuardIterator];
			Guard_j += movementGuard_j[GuardIterator];

			GuardIterator++;
		}

		//drunk
		else if (ranGuard == 1) 
		{
			int reverse;
			int stops = (int) Math.floor(Math.random()*2);

			if (stops == 0) //normal movement
			{
				if (GuardSprite == 'g') //if it stopped earlier
				{
					reverse = (int) Math.floor(Math.random()*2);
					if (reverse == 0) //doesn't change path
					{
						if(GuardIterator == 24)
							GuardIterator = 0;

						Guard_i += movementGuard_i[GuardIterator];
						Guard_j += movementGuard_j[GuardIterator];
						GuardIterator++;

					}
					else //changes path
					{
						if(GuardIterator == 0)
							GuardIterator = 24;
						
						
						GuardIterator--;
						Guard_i -= movementGuard_i[GuardIterator];
						Guard_j -= movementGuard_j[GuardIterator];
						
					}
				}
				

				else
				{
					if(GuardIterator == 24)
						GuardIterator = 0;

					Guard_i += movementGuard_i[GuardIterator];
					Guard_j += movementGuard_j[GuardIterator];

					GuardIterator++;					
				}

				GuardSprite = 'G';
			}

			else //will fall asleep
				GuardSprite = 'g';
		}

		else if (ranGuard == 2) //Suspicious
		{
			int reverse = (int) Math.floor(Math.random()*2);

			if (reverse == 0) //doesn't change path
			{
				if(GuardIterator == 24)
					GuardIterator = 0;

				Guard_i += movementGuard_i[GuardIterator];
				Guard_j += movementGuard_j[GuardIterator];
				GuardIterator++;

			}
			else //changes path
			{
				if(GuardIterator == 0)
					GuardIterator = 24;

				GuardIterator--;
				Guard_i -= movementGuard_i[GuardIterator];
				Guard_j -= movementGuard_j[GuardIterator];
				
			}
		}
	}

	public void setSprite(char s){this.GuardSprite=s;}

	public Club getClub(){
		Club c = new Club();
		return c;
	}
	public boolean hasClub(){return false;}
	
	public int getRanGuard(){
		return this.ranGuard;
	}
}
