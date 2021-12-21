package util.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class CompareInfo implements Serializable {
    private Integer AlarmEvent;

    private Integer Liveness;

    private Attribute attribute=new Attribute();

    private String CompareTime;

    private Integer PersonType;

    private Integer VisitsCount;

    private double Similarity;

    private PersonInfo personInfo=new PersonInfo();

    public Integer getAlarmEvent() {
        return AlarmEvent;
    }

    public void setAlarmEvent(Integer alarmEvent) {
        AlarmEvent = alarmEvent;
    }

    public Integer getLiveness() {
        return Liveness;
    }

    public void setLiveness(Integer liveness) {
        Liveness = liveness;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getCompareTime() {
        return CompareTime;
    }

    public void setCompareTime(String compareTime) {
        CompareTime = compareTime;
    }

    public Integer getPersonType() {
        return PersonType;
    }

    public void setPersonType(Integer personType) {
        PersonType = personType;
    }

    public Integer getVisitsCount() {
        return VisitsCount;
    }

    public void setVisitsCount(Integer visitsCount) {
        VisitsCount = visitsCount;
    }

    public double getSimilarity() {
        return Similarity;
    }

    public void setSimilarity(double similarity) {
        Similarity = similarity;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public class Attribute implements Serializable{
        private Integer Age;

        private Integer Gender;

        private Integer Glasses;

        private Integer Mask;

        private Integer Race;

        private Integer Beard;


        public Integer getAge() {
            return Age;
        }

        public void setAge(Integer age) {
            Age = age;
        }

        public Integer getGender() {
            return Gender;
        }

        public void setGender(Integer gender) {
            Gender = gender;
        }

        public Integer getGlasses() {
            return Glasses;
        }

        public void setGlasses(Integer glasses) {
            Glasses = glasses;
        }

        public Integer getMask() {
            return Mask;
        }

        public void setMask(Integer mask) {
            Mask = mask;
        }

        public Integer getRace() {
            return Race;
        }

        public void setRace(Integer race) {
            Race = race;
        }

        public Integer getBeard() {
            return Beard;
        }

        public void setBeard(Integer beard) {
            Beard = beard;
        }
    }

}
