public class SList {
  private int size ;
  private Node head;
  
  public SList(Node head){
    this.head=head;
    size=1;
  }
  public SList(){
    this.head=null;
    size=0;
  }
  /*GETTERS & SETTERS*/
  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Node getHead() {
    return head;
  }

  public void setHead(Node head) {
    this.head = head;
  }
  /*METHODS*/
  public boolean insert(Node node){
    try {
      if (head==null) {
        head=node;
        size++;
        return true;
      }
      Node iterator=head;
      //go to last node
      while (iterator.next!=null) 
        iterator=iterator.next;
      
      iterator.next=node;
      this.size++;
      return true;

    } catch (Exception e) {
      System.err.println("Unnable to insert node into SLL");
      return false;
    }
  }

  public boolean remove(Node node){
    try {
      Node iterator=head;
      //try and find the node before "node"
      while (!(iterator.next.equals(node))& iterator.next!=null)
        iterator=iterator.next;

      if (iterator.next.equals(node)) {
        //remove "node"
        iterator.next=iterator.next.next;
        this.size--;
        return true;
      }

      //"node" not found
      else
        return false;
      
    } catch (Exception e) {
      System.err.println("Unnable to remove node in SLL");
      return false;
    }
  }

  public Node search(String fileName){
    //returns node with the given file name return null if not found
    Node iterator=head;
    while (iterator.next!= null){
      if (iterator.getFileName().equalsIgnoreCase(fileName)) {
        return iterator;
      }
      iterator=iterator.next;
    }
    return null;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    Node iterator=head;
    for (int i = 1; i <= size; i++) {
      sb.append(i+" "+iterator.toString()+"\n");
    }    
    return sb.toString();
  }
}
