package net.nuttle.pattern;

public class CommandInventoryImpl extends AbstractCommand implements Command {

  private int id;
  private int inventory;
  
  private CommandInventoryImpl(int id) {
    this.id = id;
  }
  
  @Override
  public void run() {
    inventory = getSimpleService().getInventory(id);
  }
  
  public int getInventory() {
    return inventory;
  }
  
  public static class Builder {
    private int id;
    private Builder() {}
    public static Builder instance() {
      return new Builder();
    }
    public CommandInventoryImpl build() {
      return new CommandInventoryImpl(id);
    }
  }
}
