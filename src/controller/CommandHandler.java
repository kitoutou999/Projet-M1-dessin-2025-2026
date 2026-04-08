package controller;

import controller.commands.OperationCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de commandes pour le mecanisme undo/redo.
 * Conserve deux piles : les commandes effectuees et les commandes annulees.
 */
public class CommandHandler {

    private List<OperationCommand> doneCommands = new ArrayList<>();
    private List<OperationCommand> undoneCommands = new ArrayList<>();

    public void executeCommand(OperationCommand command) {
        command.operate();
        doneCommands.add(command);
        undoneCommands.clear();
    }

    public void undo() {
        if (doneCommands.isEmpty()) return;
        OperationCommand last = doneCommands.removeLast();
        last.compensate();
        undoneCommands.add(last);
    }

    public void redo() {
        if (undoneCommands.isEmpty()) return;
        OperationCommand last = undoneCommands.removeLast();
        last.operate();
        doneCommands.add(last);
    }

    public void reset() {
        doneCommands.clear();
        undoneCommands.clear();
    }
}
