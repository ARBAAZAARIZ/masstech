package service;

import dao.VisitorApprovalDAO;
import model.VisitorApprovalLog;

public class VisitorApprovalService {

    private final VisitorApprovalDAO dao = new VisitorApprovalDAO();

    public boolean logVisitorEntry(String visitorName, String purpose, long flatId, String photoPath) {
        Long memberId = dao.getMemberIdByFlatId(flatId);

        if (memberId == null) {
            return false; // No active occupant found
        }

        VisitorApprovalLog log = new VisitorApprovalLog();
        log.setVisitorName(visitorName);
        log.setPurpose(purpose);
        log.setFlatId(flatId);
        log.setMemberId(memberId);
        log.setVisitorProfilePhoto(photoPath);
        log.setStatus("Pending");

        return dao.insertVisitorLog(log);
    }
}
