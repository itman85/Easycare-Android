package vn.easycare.layers.ui.components.data;

import android.graphics.Bitmap;

import java.io.Serializable;

import vn.easycare.layers.ui.components.data.base.IBaseItemData;

/**
 * Created by phan on 12/16/2014.
 */
public class PatientManagementItemData implements Serializable,IBaseItemData {
    String PatientId;
    String PatientAvatar;
    String PatientAvatarThumb;
    String PatientName;
    String PatientBirthday;
    String PatientPhoneNumber;
    String PatientEmailAddress;
    String PatientAddress;
    int PatientOrderCount;
    int PatientCancelCount;
    int PatientChangeCount;
    int PatientCommentCount;

    int totalPages;
    int currentPage;
    int lastPage;
    int itemsPerPage;

    boolean IsPatientBlocked;

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientAvatar() {
        return PatientAvatar;
    }

    public void setPatientAvatar(String patientAvatar) {
        PatientAvatar = patientAvatar;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return PatientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        PatientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientEmailAddress() {
        return PatientEmailAddress;
    }

    public void setPatientEmailAddress(String patientEmailAddress) {
        PatientEmailAddress = patientEmailAddress;
    }

    public String getPatientBirthday() {
        return PatientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        PatientBirthday = patientBirthday;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }

    public int getPatientOrderCount() {
        return PatientOrderCount;
    }

    public void setPatientOrderCount(int patientOrderCount) {
        PatientOrderCount = patientOrderCount;
    }

    public int getPatientCancelCount() {
        return PatientCancelCount;
    }

    public void setPatientCancelCount(int patientCancelCount) {
        PatientCancelCount = patientCancelCount;
    }

    public int getPatientChangeCount() {
        return PatientChangeCount;
    }

    public void setPatientChangeCount(int patientChangeCount) {
        PatientChangeCount = patientChangeCount;
    }

    public int getPatientCommentCount() {
        return PatientCommentCount;
    }

    public void setPatientCommentCount(int patientCommentCount) {
        PatientCommentCount = patientCommentCount;
    }

    public boolean isPatientBlocked() {
        return IsPatientBlocked;
    }

    public void setPatientBlocked(boolean isPatientBlocked) {
        IsPatientBlocked = isPatientBlocked;
    }

    public String getPatientAvatarThumb() {
        return PatientAvatarThumb;
    }

    public void setPatientAvatarThumb(String patientAvatarThumb) {
        PatientAvatarThumb = patientAvatarThumb;
    }

    public int getTotalItems() {
        return totalPages;
    }

    public void setTotalItems(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
