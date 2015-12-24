<script>
   function GCSMUser(firstName, lastName, email) 
   {
     this.firstName = firstName;
     this.lastName = lastName;
     this.email = email;

     this.name = this.firstName + " "+ this.lastName;
   }

   var userList = new Array(3);

   userList[0] = new GCSMUser('Kim','Ken','kkim@bms.com');
   userList[1] = new GCSMUser('John','Jones','jjones@bms.com');
   userList[2] = new GCSMUser('Damon','Jones','djones@bms.com');

   function findUsers()
   {
   
   }
</script>

