
package rs.andrej.gadgets.controller;


public enum Actions {
    
    LIST("list"),
    ADD("add"), 
    EDIT("edit"),  
    DELETE("delete"); 

    private final String action;

    private Actions (String action) {
        this.action = action;
    }


    
    public static Actions getForAction(String action) {
        for (Actions actions : values()) {
            if (actions.action.equals(action)) {
                return actions;
            }
        }
        return LIST;
    }

    
}
