package com.technocrats.ryftofficialapp;

public class Model {
    String techUsed1,techUsed2,title,projectImageUrl,Description,techUsed3,techUsed4,step1
            ,step2,step3,step4,projectId;

    public Model() {
    }

    public Model(String techUsed1, String techUsed2, String title,String projectImageUrl,
                 String Description,String techUsed3,String techUsed4,String step1,
                 String step2,String step3,String step4,String projectId) {
        this.step1=step1;
        this.step2=step2;
        this.step3=step3;
        this.step4=step4;
        this.projectId=projectId;
        this.techUsed3=techUsed3;
        this.techUsed4=techUsed4;
        this.techUsed1 = techUsed1;
        this.techUsed2 = techUsed2;
        this.title = title;
        this.projectImageUrl=projectImageUrl;
        this.Description=Description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStep1() {
        return step1;
    }

    public String getStep2() {
        return step2;
    }

    public String getStep3() {
        return step3;
    }

    public String getStep4() {
        return step4;
    }

    public String getTechUsed3() {
        return techUsed3;
    }

    public String getTechUsed4() {
        return techUsed4;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

    public void setStep4(String step4) {
        this.step4 = step4;
    }

    public void setTechUsed3(String techUsed3) {
        this.techUsed3 = techUsed3;
    }

    public void setTechUsed4(String techUsed4) {
        this.techUsed4 = techUsed4;
    }

    public String getDescription(){
        return Description;
        }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTechUsed1()
    {
        return techUsed1;
    }

    public void setTechUsed1(String techUsed1)
    {
        this.techUsed1 = techUsed1;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getTechUsed2() {
        return techUsed2;
    }

    public void setTechUsed2(String techUsed2) {
        this.techUsed2 = techUsed2;
    }

    public String getProjectImageUrl() {
        return projectImageUrl;
    }

    public void setProjectImageUrl(String projectImageUrl) {
        this.projectImageUrl = projectImageUrl;
    }
}
