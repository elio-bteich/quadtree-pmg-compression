import java.util.ArrayList;

public class Compression {
    public static void main(String[] args) {
        MainMenu MM;
        DefaultMenu DM;
        ArrayList <String> images = new ArrayList<>();

        if(args.length > 0){
            int rho = Integer.valueOf(args[1]);
            if(rho < 1 || rho > 100){
                DM = new DefaultMenu(args[0],rho );
                DM.start();
            }else{
                System.out.println("ERROR: INVALID RHO VALUE!");
            }
           
        }else{
            images.add("train.pgm");
            images.add("flower_small.pgm");
            images.add("flower.pgm");
            images.add("lighthouse_big.pgm");
            images.add("lighthouse.pgm");
            images.add("tree_big.pgm");
            images.add("tree.pgm");
            MM =new MainMenu(images);
            MM.start();
        }
    }
    
}
