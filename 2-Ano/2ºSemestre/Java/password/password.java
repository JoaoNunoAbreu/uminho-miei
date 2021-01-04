public class password{

    public static boolean passwordRec(char pass[], char gerada[], int i, int k){

        int x;

        if(i == k) return (pass == gerada);
        else for(x = 0; x < k; x++) {
            gerada[i] = (char)x;
            if(passwordRec(pass,gerada,i+1,k)) return true;
        }
        return false;
    }

    public static boolean password(char pass[], int k){

        char gerada[] = {'-1','-1','-1','-1','-1'};
        return (passwordRec(pass,gerada,0,k));
    }

    public static void main(String [] args){
        
    }


}