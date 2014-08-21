package projecteuler;
import java.util.Scanner;
import java.util.ArrayList;

public class Mainprojecteulerclass {
        public static long letsdoit(int powerofn, int oripower, long[] originalseq){
                //int completenum=(powerofn*(powerofn+1))/2;
                int completenum=powerofn+1;
                //System.out.println("Stage "+powerofn);
                long[] complete= new long[completenum];
                for(int j=0;j<=powerofn;j++){
                        complete[j]=originalseq[j];
                        //System.out.println(complete[j]);
                }
                long abc=0;
                for(int j=0; j<completenum-1;j++){
                        complete[j]=complete[j+1]-complete[j];
                        abc=complete[j];
                        //System.out.println("hey "+abc);
                }
                powerofn--;

                if(powerofn==0){
                        for(int i=1;i<oripower;i++){
                                //System.out.println("get "+abc+" divided by "+(i+1));
                                abc=abc/(i+1);
                        }
                        return abc;
                }
                return letsdoit(powerofn,oripower,complete);
        }
        public static long[] converter(long[] oriseq,long[] realori, int powerofn){
                long[] arraycoeff= new long[powerofn+1];
                //long[] realori=oriseq;
                arraycoeff[0]=letsdoit(powerofn,powerofn,oriseq);
                //System.out.println("the coeff of 0 "+arraycoeff[0]);
                int lol=powerofn;
                for(int i=1;i<powerofn;i++){
                        for(int j=1;j<=lol;j++){
                                oriseq[j-1]=oriseq[j-1]-(long)Math.pow(j,lol)*arraycoeff[i-1];
                                //System.out.println(oriseq[j-1]+" with coeff "+arraycoeff[i-1]+"and power"+(long)Math.pow(j,powerofn));
                        }
                        lol--;
                        arraycoeff[i]=letsdoit(lol,lol,oriseq);
                        //System.out.println("original "+letsdoit(powerofn-i,powerofn-i,oriseq));
                }
                arraycoeff[arraycoeff.length-1]=0;
                //System.out.println("array length "+arraycoeff.length);
                int d=arraycoeff.length;
                for(int a=0;a<arraycoeff.length-1;a++){
                arraycoeff[d-1]=arraycoeff[a]+arraycoeff[d-1];
                }
                //System.out.println("THE REAL ORI "+ realori[0]);
                arraycoeff[d-1]=realori[0]-arraycoeff[d-1];
                for(int b=0;b<arraycoeff.length;b++){
                //System.out.println(arraycoeff[b]);
                }
                return arraycoeff;
        }
        public static void printcoeff(long[] coeff){
                int a=coeff.length-1;
                String say="";
                for(int i=0;i<=a;i++){
                	long bob=coeff[a-i];
                	if(bob==0) continue;
                        if(i>1){
                        	if(bob<0){
                        	bob=0-bob;
                        	say=" - "+bob+"n"+i+say;
                        	}else{
                        	if(i==a){say=bob+"n"+i+say;}else{
                        	say=" + "+bob+"n"+i+say;}}
                        }else
                        if(i==1){
                        	if(bob<0){
                            	bob=0-bob;
                            	say=" - "+bob+"n"+say;
                            	}else if(bob>1){
                            	say=" + "+bob+"n"+say;}
                            	else if(bob==1){
                            		say=" + n"+say;
                            	}
                        	}
                        else{
                        	if(bob<0){
                            	bob=0-bob;
                            	say=say+" - "+bob;
                            	}else{
                        	say=" + "+bob;}}
                }
                if(say.substring(0,3).equals(" + ")){
                	say=say.substring(3);
                }
                System.out.println(say);
        }
        public static long[] sequencer(int exppow,long[] eqcoeff){
        	long[] outputseq=new long[exppow+2];
        	int colength=eqcoeff.length;
        	String say="";
        	for(int j=0;j<=exppow+1;j++){
        	for(int i=0;i<colength;i++){
        		outputseq[j]=outputseq[j]+((long)Math.pow((j+1),(colength-i-1))*eqcoeff[i]);
        	}
        	if(j==0)say=say+outputseq[j]+" , ";
        	if(j<=exppow && j!=0)say=say+outputseq[j]+" , ";
        	if(j==exppow+1)say=say+outputseq[j];
        	}
        	System.out.println(say);
        	return outputseq;
        }
        public static void main(String[] args) {
                int totalpower=5;
                System.out.println("The original sequence:");
                long[] oricoeff={1,0,6,4,1};
                long[] neworiseq=sequencer(totalpower-1,oricoeff);
                long[] abc=new long[totalpower+1];
                for(int i=1;i<=totalpower+1;i++){
                    abc[i-1]=neworiseq[i-1];
            }
                long[] coefflong=converter(abc,neworiseq,totalpower);
                System.out.println("Obtained polynomial:");
                printcoeff(coefflong);
                sequencer(totalpower,coefflong);
                System.out.println("Continued original sequence:");
                sequencer(totalpower,oricoeff);
                System.out.println("Enter the number and type anything but number if you are done");
                Scanner sc=new Scanner(System.in);
                ArrayList<Long> longlist = new ArrayList<Long>();
                while(sc.hasNextLong()){
                	//inputnum=sc.nextLong()+inputnum;
                	longlist.add(sc.nextLong());
                }
                sc.close();
                int longlength=longlist.size();
                long[] inputnumbers= new long[longlength];
                String inputtedseq="";
                for(int a=0;a<longlength;a++){
                	inputnumbers[a]=longlist.get(a);
                	if(a==0){inputtedseq=inputnumbers[a]+" , ";}
                	else if(a==longlength-1){inputtedseq=inputtedseq+inputnumbers[a];}
                	else{inputtedseq=inputtedseq+inputnumbers[a]+" , ";}
                }
                System.out.println("Inputted sequence "+inputtedseq);
                long[] bcd=new long[longlength];
                for(int i=0;i<longlength;i++){
                    bcd[i]=inputnumbers[i];
            }
                System.out.println("Obtained polynomial:");
                long[] obtainedcoeff= converter(bcd,inputnumbers,longlength-1);
                printcoeff(obtainedcoeff);
                System.out.println("Output sequence: ");
                sequencer(longlength-1,obtainedcoeff);
        }
}