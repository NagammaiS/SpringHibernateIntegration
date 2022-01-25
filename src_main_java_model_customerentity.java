@Entity
@Table(name="customer")
public class Customer {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 @Column(name = "firstname")
 private String firstname;
 
 @Column(name = "lastname")
 private String lastname;
 
 @Column(name = "gender")
 private String gender;
 
 @Column(name = "address")
 private String address;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getFirstname() {
  return firstname;
 }

 public void setFirstname(String firstname) {
  this.firstname = firstname;
 }

 public String getLastname() {
  return lastname;
 }

 public void setLastname(String lastname) {
  this.lastname = lastname;
 }

 public String getGender() {
  return gender;
 }

 public void setGender(String gender) {
  this.gender = gender;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }
}