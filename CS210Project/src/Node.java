public class Node {
    private String fileName;
    private int lineNumber;
    public Node next;
    
    //No next node constructor
    public Node(String fileName, int lineNumber){
        this.fileName=fileName;
        this.lineNumber=lineNumber;
        next=null;
    }

    //Full argument constructor
    public Node(String fileName, int lineNumber, Node next){
        this.fileName=fileName;
        this.lineNumber=lineNumber;
        this.next=next;
    }

    /*GETTERS & SETTERS*/
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /*METHODS*/
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Node))
        return false;
        Node n=(Node)o;
        if (this.fileName.equalsIgnoreCase(n.fileName) & this.lineNumber==n.lineNumber) {
            return true;
        }
        else
            return false;
    }
    @Override
    public String toString(){
        return this.getFileName()+" "+this.getLineNumber();
    }
}

