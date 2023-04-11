package org.example.model.figure;

public class ForwardFigure implements Figure{
    public ForwardFigure(String name, Integer attack, Integer defense){
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    private String name;
    private Integer attack;
    private Integer defense;

    private String move;

    private String trace;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAttack(Integer attack_value) {
        attack = attack_value;
    }

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setDefense(Integer defense_value) {
        defense = defense_value;
    }

    @Override
    public Integer getDefense() {
        return defense;
    }

    @Override
    public String getMove() {
        return move;
    }

    @Override
    public String getTrace() {
        return "Diagonal";
    }

}
//
//    rivate String calcMove(String trace){
//    String [] arguments = trace.split("@");
//    String command = arguments[0];
//    StringBuilder move = new StringBuilder();
//    switch (command){
//        case "forward": for (int i = 1; i < arguments.length; i++){
//            move.append(" 0 ").append(arguments[i]);
//        }
//        case "diagonal": if (arguments.length == 1){
//            move.append("1 1 2 2 3 3 4 4 5 5 6 6 7 7");
//        }
//        else{
//            for (int i = 1; i <arguments.length;i++){
//                move.append(" ").append(arguments[i]).append(" ").append(arguments[i]);
//            }
//        }
//        case "crosshair": if (arguments.length == 1){
//            move.append("0 1 0 2 0 3 0 4 0 5 0 6 0 7 1 0 2 0 3 0 4 0 5 0 6 0 7 0");
//        }
//        else{
//            for (int i = 1; i <arguments.length;i++){
//                move.append(" ").append(arguments[i]).append(" ").append(arguments[i]);
//            }
//        }
//        case "horse": move.append("2 1");
//        case "line": for (int i = 1; i <arguments.length;i++){
//            move.append("0 ").append(arguments[i]);
//        }
