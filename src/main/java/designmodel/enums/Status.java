package designmodel.enums;

public enum Status {
    NEW(0){
        void run(){
            System.out.println(this.statusCode);
        }
    },
    RUNNABLE(1){
        void run(){
            System.out.println(this.statusCode);
        }
    };

    public int statusCode;
    abstract void run();
    Status(int statusCode){
        this.statusCode = statusCode;
    }

    public static void main(String[] args) {
        Status status = Status.valueOf("0");
    }
}
