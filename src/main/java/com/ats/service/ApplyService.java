package com.ats.service;

public interface ApplyService {
    boolean create(int JobSeekerID, int JobID);

    boolean check(int JobSeekerID, int JobID);
}
