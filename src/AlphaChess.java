import javax.swing.*;

public class AlphaChess {
    static String ChessBoard[][]={
        {"r","k","b","q","a","b","k","r"},
        {"p","p","p","p","p","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"},
    };
    static int kingPosC,kingPosL,kingSafeC;
    public static void main(String[] args) throws Exception {
        while(!"A".equals(ChessBoard[kingPosC/8][kingPosC%8])){kingPosC++;}
        while(!"a".equals(ChessBoard[kingPosL/8][kingPosL%8])){kingPosL++;}
        // JFrame f = new JFrame("Chess");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // UserInteface ui = new UserInteface();
        // f.add(ui);
        // f.setSize(500, 500);
        // f.setVisible(true);
        System.out.println(possibleMove());
    }
    static String possibleMove(){
        String list ="";
        for(int i=0;i<64;i++){
            switch(ChessBoard[i/8][i%8]){
                case "P": list+=possibleP(i);
                    break;
                case "K": list+=possibleK(i);
                    break;
                case "R":list+=possibleR(i);
                    break;
                case "B":list+=possibleB(i);
                    break;
                case "Q":list+=possibleQ(i);
                    break;
                case "A":list+=possibleA(i);
                    break;        
            }
        }
        return list;
    }
    public static String possibleP(int i){
        String list ="",oldPice;
        int r =i/8,c =i%8;
        
        for(int j=-1;j<=1;j+=2){
            try{//capture
                if(Character.isLowerCase(ChessBoard[r-1][c+j].charAt(0))&&i>=16){
                    
                    oldPice = ChessBoard[r-1][c+j];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r-1][c+j] = "P";
                    if(kingSafe()){
                        list =list+r+c+(r-1)+(c+j)+oldPice;
                    }
                    ChessBoard[r][c] ="P";
                    ChessBoard[r-1][c+j] = oldPice;
                }
            }catch(Exception e){

            }
            try{//capture and promotion
                if(Character.isLowerCase(ChessBoard[r-1][c+j].charAt(0))&&i<16){
                    String[] temp ={"Q","R","B","K"};
                    for(int k=0;k<4;k++){
                        oldPice = ChessBoard[r-1][c+j];
                        ChessBoard[r][c] =" ";
                        ChessBoard[r-1][c+j] = temp[k];
                        if(kingSafe()){
                            //column1,column2,captured-piece,new-piece,P(promotion)
                            list =list+c+(c+j)+oldPice+temp[k]+"P";
                        }
                        ChessBoard[r][c] ="P";
                        ChessBoard[r-1][c+j] = oldPice;
                    }
                    
                }
            }catch(Exception e){

            }
        }
        try{//move one up
                if(" ".equals(ChessBoard[r-1][c])&& i>=16){
                    
                    oldPice = ChessBoard[r-1][c];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r-1][c] = "P";
                    if(kingSafe()){
                        list =list+r+c+(r-1)+(c)+oldPice;
                    }
                    ChessBoard[r][c] ="P";
                    ChessBoard[r-1][c] = oldPice;
                }
            }catch(Exception e){}
            try{//move one up and promotion and no capture
                if(" ".equals(ChessBoard[r-1][c])&& i<16){
                    
                    String[] temp ={"Q","R","B","K"};
                    for(int k=0;k<4;k++){
                        oldPice = ChessBoard[r-1][c];
                        ChessBoard[r][c] =" ";
                        ChessBoard[r-1][c] = temp[k];
                        if(kingSafe()){
                            //column1,column2,captured-piece,new-piece,P(promotion)
                            list =list+c+(c)+oldPice+temp[k]+"P";
                        }
                        ChessBoard[r][c] ="P";
                        ChessBoard[r-1][c] = oldPice;
                    }
                }
            }catch(Exception e){}
            try{//move two up
                if(" ".equals(ChessBoard[r-1][c])&&" ".equals(ChessBoard[r-2][c])&& i>=48){
                    
                    oldPice = ChessBoard[r-2][c];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r-2][c] = "P";
                    if(kingSafe()){
                        list =list+r+c+(r-2)+(c)+oldPice;
                    }
                    ChessBoard[r][c] ="P";
                    ChessBoard[r-2][c] = oldPice;
                }
            }catch(Exception e){}
        return list;
    }
    public static String possibleR(int i){
        String list ="",oldPice;
        int r =i/8,c =i%8;
        int temp =1;
        for(int j=-1;j<=1;j+=2){
            try{
                while(" ".equals(ChessBoard[r][c+temp*j])){
                    oldPice = ChessBoard[r][c+temp*j];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r][c+temp*j] = "R";
                    if(kingSafe()){
                        list =list+r+c+r+(c+temp*j)+oldPice;
                    }
                    ChessBoard[r][c] ="R";
                    ChessBoard[r][c+temp*j] = oldPice;
                    temp++;
                }
                if(Character.isLowerCase(ChessBoard[r][c+temp*j].charAt(0))){
                    oldPice = ChessBoard[r][c+temp*j];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r][c+temp*j] = "R";
                    if(kingSafe()){
                        list =list+r+c+r+(c+temp*j)+oldPice;
                    }
                    ChessBoard[r][c] ="R";
                    ChessBoard[r][c+temp*j] = oldPice;
                    
                }
            }catch(Exception e){}
            temp =1;
            try{
                while(" ".equals(ChessBoard[r+temp*j][c])){
                    oldPice = ChessBoard[r+temp*j][c];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r+temp*j][c] = "R";
                    if(kingSafe()){
                        list =list+r+c+(r+temp*j)+(c)+oldPice;
                    }
                    ChessBoard[r][c] ="R";
                    ChessBoard[r+temp*j][c] = oldPice;
                    temp++;
                }
                if(Character.isLowerCase(ChessBoard[r+temp*j][c].charAt(0))){
                    oldPice = ChessBoard[r+temp*j][c];
                    ChessBoard[r][c] =" ";
                    ChessBoard[r+temp*j][c] = "R";
                    if(kingSafe()){
                        list =list+r+c+(r+temp*j)+(c)+oldPice;
                    }
                    ChessBoard[r][c] ="R";
                    ChessBoard[r+temp*j][c] = oldPice;
                    
                }
            }catch(Exception e){}
            temp =1;
        }
        return list;
    }
    public static String possibleB(int i){
        String list ="",oldPice;
        int r =i/8,c=i%8;
        int temp =1;
        //x and y axis
        for(int j=-1;j<=1;j+=2){
            for(int k=-1;k<=1;k+=2){
                
                    try{
                        while(" ".equals(ChessBoard[r+temp*j][c+temp*k])){
                            oldPice =ChessBoard[r+temp*j][c+temp*k];
                            ChessBoard[r][c] =" ";
                            ChessBoard[r+temp*j][c+temp*k] ="B";
                            if(kingSafe()){
                                    list=list+r+c+(r+temp*j)+(c+temp*k)+oldPice;
                            }
                            ChessBoard[r][c] ="B";
                            ChessBoard[r+temp*j][c+temp*k] =oldPice;
                            temp++;
                        }
                        if(Character.isLowerCase(ChessBoard[r+temp*j][c+temp*k].charAt(0))){
                            oldPice =ChessBoard[r+temp*j][c+temp*k];
                            ChessBoard[r][c] =" ";
                            ChessBoard[r+temp*j][c+temp*k] ="B";
                            if(kingSafe()){
                                    list=list+r+c+(r+temp*j)+(c+temp*k)+oldPice;
                            }
                            ChessBoard[r][c] ="B";
                            ChessBoard[r+temp*j][c+temp*k] =oldPice;
                        }
                    }catch(Exception e){}
                    temp =1;
                
            }
        }
        return list;
    }
    public static String possibleQ(int i){
        String list ="",oldPice;
        int r =i/8,c=i%8;
        int temp =1;
        //x and y axis
        for(int j=-1;j<=1;j++){
            for(int k=-1;k<=1;k++){
                if(j!=0||k!=0){
                    try{
                        while(" ".equals(ChessBoard[r+temp*j][c+temp*k])){
                            oldPice =ChessBoard[r+temp*j][c+temp*k];
                            ChessBoard[r][c] =" ";
                            ChessBoard[r+temp*j][c+temp*k] ="Q";
                            if(kingSafe()){
                                    list=list+r+c+(r+temp*j)+(c+temp*k)+oldPice;
                            }
                            ChessBoard[r][c] ="Q";
                            ChessBoard[r+temp*j][c+temp*k] =oldPice;
                            temp++;
                        }
                        if(Character.isLowerCase(ChessBoard[r+temp*j][c+temp*k].charAt(0))){
                            oldPice =ChessBoard[r+temp*j][c+temp*k];
                            ChessBoard[r][c] =" ";
                            ChessBoard[r+temp*j][c+temp*k] ="Q";
                            if(kingSafe()){
                                    list=list+r+c+(r+temp*j)+(c+temp*k)+oldPice;
                            }
                            ChessBoard[r][c] ="Q";
                            ChessBoard[r+temp*j][c+temp*k] =oldPice;
                        }
                    }catch(Exception e){}
                    temp =1;
                }
            }
        }
        return list;
    }
    public static String possibleK(int i){
        String list ="",oldPice;
        int r =i/8,c=i%8;
        
        for(int j=-1;j<=1;j+=2){
            for(int k=-1;k<=1;k+=2){
                try{
                    if(Character.isLowerCase(ChessBoard[r+j][c+k*2].charAt(0))||" ".equals(ChessBoard[r+j][c+k*2])){
                        oldPice =ChessBoard[r+j][c+k*2];
                        ChessBoard[r][c] =" ";
                        ChessBoard[r+j][c+k*2] ="K";
                        if(kingSafe()){
                            list=list+r+c+(r+j)+(c+k*2)+oldPice;
                        }
                        ChessBoard[r][c] ="K";
                        ChessBoard[r+j][c+k*2] =oldPice;
                    }
                }catch(Exception e){}
                try{
                    if(Character.isLowerCase(ChessBoard[r+j*2][c+k].charAt(0))||" ".equals(ChessBoard[r+j*2][c+k])){
                        oldPice =ChessBoard[r+j*2][c+k];
                        ChessBoard[r][c] =" ";
                        ChessBoard[r+j*2][c+k] ="K";
                        if(kingSafe()){
                            list=list+r+c+(r+j*2)+(c+k)+oldPice;
                        }
                        ChessBoard[r][c] ="K";
                        ChessBoard[r+j*2][c+k] =oldPice;
                    }
                }catch(Exception e){}
            }
        }
        return list;
    }
    public static String possibleA(int i){
        String list ="",oldPice;
        int r =i/8,c=i%8;
        
        for(int j=0;j<9;j++){
           
                if(j!=4){
                    try{
                        if(Character.isLowerCase(ChessBoard[r-1+j/3][c-1+j%3].charAt(0))||" ".equals(ChessBoard[r-1+j/3][c-1+j%3])){
                            oldPice = ChessBoard[r-1+j/3][c-1+j%3];
                            ChessBoard[r][c] =" ";
                            ChessBoard[r-1+j/3][c-1+j%3]="A";
                            
                            int kingTemp=kingPosC;
                            kingPosC =i+(j/3)*8+j%3-9;
                            if(kingSafe()){
                                list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPice;
                            }
                            ChessBoard[r][c] ="A";
                            ChessBoard[r-1+j/3][c-1+j%3]=oldPice;
                            kingPosC =kingTemp;
                            
                        }
                    }catch(Exception e){}
                }
            }
        

        return list;
    }
    public static boolean kingSafe(){
        //bishop ,queen
        int temp =1;
        
        for(int i=-1;i<=1;i+=2){
            for(int j=-1;j<=1;j+=2){
                    try{
                        while(" ".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8+temp*j])){temp++;}
                        if("b".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8+temp*j])||"q".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8+temp*j])){
                            //danger
                            return false;
                        }
                           
                    }catch(Exception e){}
                    temp =1;
            }
        }
        //Rook
        for(int i=-1;i<=1;i+=2){
            try{
                while(" ".equals(ChessBoard[kingPosC/8][kingPosC%8+temp*i])){temp++;}
                if("r".equals(ChessBoard[kingPosC/8][kingPosC%8+temp*i])||"q".equals(ChessBoard[kingPosC/8][kingPosC%8+temp*i])){
                            //danger
                    return false;
                }
            }
            catch(Exception e){}
                temp =1;
            try{
                while(" ".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8])){temp++;}
                if("r".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8])||"q".equals(ChessBoard[kingPosC/8+temp*i][kingPosC%8])){
                            //danger
                    return false;
                }
            }
            catch(Exception e){}
            temp=1;
        }
        //knight
        for(int i=-1;i<=1;i+=2){
            for(int j=-1;j<=1;j+=2){
                    try{
                        if("b".equals(ChessBoard[kingPosC/8+i][kingPosC%8+j*2])){
                            //danger
                            return false;
                        }
                           
                    }catch(Exception e){}
                    try{
                        if("b".equals(ChessBoard[kingPosC/8+i*2][kingPosC%8+j])){
                            //danger
                            return false;
                        }
                           
                    }catch(Exception e){}
            }
        } 
        //pawn
        if(kingPosC>=16){
            try{
                if("p".equals(ChessBoard[kingPosC/8-1][kingPosC%8-1])){
                            //danger
                            return false;
                    }       
            }catch(Exception e){}
            try{
                 if("p".equals(ChessBoard[kingPosC/8+1][kingPosC%8+1])){
                            //danger
                            return false;
                    }       
                }catch(Exception e){}
            //king
            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if(i!=0||j!=0){
                        
                        try{
                            if("a".equals(ChessBoard[kingPosC/8+i][kingPosC%8+j])){
                                //danger
                                return false;
                            }
                            
                        }catch(Exception e){}
                    } 
                    
                }
            }    
        }

        return true;
    }
}
