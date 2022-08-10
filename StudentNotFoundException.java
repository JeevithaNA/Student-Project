package sdbms;

 class StudentNotFoundException extends RuntimeException {
	 private String Message;
	 StudentNotFoundException(String Message){
		 this.Message=Message;
	 }
	 public String getMessage() {
		 return Message;
	 }
  
}
