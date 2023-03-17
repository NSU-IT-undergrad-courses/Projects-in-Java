package Commands;

import Context.Context;
import Exception.Command.UnprocessableArguments;
import Exception.Command.WrongElementsAmount;

public class Define extends Command {

    @Override
    public void execute(Context context, String[] arguments) {
        if (arguments.length %2 !=0){
            throw new WrongElementsAmount(arguments.length,this.getClass().getSimpleName());
        }
        for(int i = 0; i < arguments.length;i +=2){
            try {
                context.addVariables(arguments[i], Double.valueOf(arguments[i + 1]));
            } catch (Exception e) {
                throw new UnprocessableArguments(new String[]{arguments[i], arguments[i + 1]},this.getClass().getSimpleName());
            }
        }
    }
}
