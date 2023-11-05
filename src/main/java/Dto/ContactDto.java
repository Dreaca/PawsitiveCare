package Dto;

public class ContactDto {
    private String contId;
    private String contact;
    private String contact2;
    private String custId;

    public ContactDto(String contId, String contactNo, String customerId) {
        this.contId = contId;
        this.contact = contactNo;
        this.custId = customerId;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "contId='" + contId + '\'' +
                ", contact='" + contact + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", userId='" + custId + '\'' +
                '}';
    }

    public ContactDto() {
    }

    public String getContId() {
        return contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getUserId() {
        return custId;
    }

    public void setUserId(String userId) {
        this.custId = userId;
    }

    public ContactDto(String contId, String contact, String contact2, String userId) {
        this.contId = contId;
        this.contact = contact;
        this.contact2 = contact2;
        this.custId = userId;
    }

    public ContactDto(String contact) {
        this.contact = contact;
    }
}
