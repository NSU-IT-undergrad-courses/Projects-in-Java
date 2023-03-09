package Commands;

import Context.Context;

public class Define extends Command {

    @Override
    public void execute(Context context, String[] arguments) {
        if (arguments.length %2 !=0){
            //Throw exception
            System.out.println("CREATE EXCPETION HERE!!!");
        }
        for(int i = 0; i < arguments.length;i +=2){
            context.addVariables(arguments[i], Double.valueOf(arguments[i+1]));
        }
    }
}
