package util.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class PersonInfo implements Serializable {

    private String PersonId;

    private String PersonName;

    private Integer Sex;

    private String IDCard;

    private String Nation;

    private String Birthday;

    private String Phone;

    private String Address;

    private String SaveTime;

    private Integer LimitTime;

    private String StartTime;

    private String EndTime;

    private String Label;

    private String PersonPhoto;

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSaveTime() {
        return SaveTime;
    }

    public void setSaveTime(String saveTime) {
        SaveTime = saveTime;
    }

    public Integer getLimitTime() {
        return LimitTime;
    }

    public void setLimitTime(Integer limitTime) {
        LimitTime = limitTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getPersonPhoto() {
        return PersonPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        PersonPhoto = personPhoto;
    }

    public class PersonExtension implements Serializable {

        private String PersonCode1;

        private String PersonCode2;

        private String PersonCode3;

        private String PersonReserveName;

        private Integer PersonParam1;

        private Integer PersonParam2;

        private Integer PersonParam3;

        private Integer PersonParam4;

        private Integer PersonParam5;

        private String PersonData1;

        private String PersonData2;

        private String PersonData3;

        private String PersonData4;

        private String PersonData5;

        public String getPersonCode1() {
            return PersonCode1;
        }

        public void setPersonCode1(String personCode1) {
            PersonCode1 = personCode1;
        }

        public String getPersonCode2() {
            return PersonCode2;
        }

        public void setPersonCode2(String personCode2) {
            PersonCode2 = personCode2;
        }

        public String getPersonCode3() {
            return PersonCode3;
        }

        public void setPersonCode3(String personCode3) {
            PersonCode3 = personCode3;
        }

        public String getPersonReserveName() {
            return PersonReserveName;
        }

        public void setPersonReserveName(String personReserveName) {
            PersonReserveName = personReserveName;
        }

        public Integer getPersonParam1() {
            return PersonParam1;
        }

        public void setPersonParam1(Integer personParam1) {
            PersonParam1 = personParam1;
        }

        public Integer getPersonParam2() {
            return PersonParam2;
        }

        public void setPersonParam2(Integer personParam2) {
            PersonParam2 = personParam2;
        }

        public Integer getPersonParam3() {
            return PersonParam3;
        }

        public void setPersonParam3(Integer personParam3) {
            PersonParam3 = personParam3;
        }

        public Integer getPersonParam4() {
            return PersonParam4;
        }

        public void setPersonParam4(Integer personParam4) {
            PersonParam4 = personParam4;
        }

        public Integer getPersonParam5() {
            return PersonParam5;
        }

        public void setPersonParam5(Integer personParam5) {
            PersonParam5 = personParam5;
        }

        public String getPersonData1() {
            return PersonData1;
        }

        public void setPersonData1(String personData1) {
            PersonData1 = personData1;
        }

        public String getPersonData2() {
            return PersonData2;
        }

        public void setPersonData2(String personData2) {
            PersonData2 = personData2;
        }

        public String getPersonData3() {
            return PersonData3;
        }

        public void setPersonData3(String personData3) {
            PersonData3 = personData3;
        }

        public String getPersonData4() {
            return PersonData4;
        }

        public void setPersonData4(String personData4) {
            PersonData4 = personData4;
        }

        public String getPersonData5() {
            return PersonData5;
        }

        public void setPersonData5(String personData5) {
            PersonData5 = personData5;
        }
    }


}
