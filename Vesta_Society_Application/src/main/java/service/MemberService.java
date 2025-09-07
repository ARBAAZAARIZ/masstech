package service;

import java.util.List;

import dao.MemberDAO;
import model.Member;

public class MemberService {
	MemberDAO memberDAO;
	public MemberService() {
		 memberDAO=new MemberDAO();
	}
	
	public Member getMemberById(int memberId) {
		return memberDAO.getMemberById(memberId);
	}
	
	 public boolean updateMemberDetails(int memberId, String fullName, String email, String phone, String photoPath) {
	        return memberDAO.updateMember(memberId, fullName, email, phone, photoPath);
	    }
	 
	 public List<Member> getMembersBySocietyId(int societyId){
		 return memberDAO.getMembersBySocietyId(societyId);
	 }
	 
	 public boolean updateMember(Member member) {
		    return memberDAO.updateMember(member);
		}
	 
	 public List<Member> searchMembersByName(long societyId, String nameFragment) {
		    return memberDAO.getMembersByNameFragment(societyId, nameFragment);
		}



}
