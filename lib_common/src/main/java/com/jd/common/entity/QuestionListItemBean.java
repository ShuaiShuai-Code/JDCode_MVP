package com.jd.common.entity;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 封闭式问题单个题目对应的bean
 */
public class QuestionListItemBean implements Parcelable {


  private String questionId;
  private String questionLabel;
  private String questionName;
  private int questionNumber;
  private String questionType;
  private String questionUrl;
  private int status;
  private String audioName;   // test audio

  private String questionContent;   //问题对应的caption 字幕的内容


  public String getQuestionContent() {
    return questionContent;
  }

  public void setQuestionContent(String questionContent) {
    this.questionContent = questionContent;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public String getQuestionLabel() {
    return questionLabel;
  }

  public void setQuestionLabel(String questionLabel) {
    this.questionLabel = questionLabel;
  }

  public String getQuestionName() {
    return questionName;
  }

  public void setQuestionName(String questionName) {
    this.questionName = questionName;
  }

  public int getQuestionNumber() {
    return questionNumber;
  }

  public void setQuestionNumber(int questionNumber) {
    this.questionNumber = questionNumber;
  }

  public String getQuestionType() {
    return questionType;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public String getQuestionUrl() {
    return questionUrl;
  }

  public void setQuestionUrl(String questionUrl) {
    this.questionUrl = questionUrl;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.questionId);
    dest.writeString(this.questionLabel);
    dest.writeString(this.questionName);
    dest.writeInt(this.questionNumber);
    dest.writeString(this.questionType);
    dest.writeString(this.questionUrl);
    dest.writeInt(this.status);
  }

  public QuestionListItemBean() {
  }

  protected QuestionListItemBean(Parcel in) {
    this.questionId = in.readString();
    this.questionLabel = in.readString();
    this.questionName = in.readString();
    this.questionNumber = in.readInt();
    this.questionType = in.readString();
    this.questionUrl = in.readString();
    this.status = in.readInt();
  }

  public static final Parcelable.Creator<QuestionListItemBean> CREATOR =
      new Parcelable.Creator<QuestionListItemBean>() {
        @Override
        public QuestionListItemBean createFromParcel(Parcel source) {
          return new QuestionListItemBean(source);
        }

        @Override
        public QuestionListItemBean[] newArray(int size) {
          return new QuestionListItemBean[size];
        }
      };
}
