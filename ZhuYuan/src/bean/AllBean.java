package bean;

import java.util.ArrayList;

public class AllBean {
	SelectBean sb = new SelectBean();
	ArrayList al = null;
	
	public ArrayList getAllAdmin(String id){
		String sql = "select * from admin where id!=1 and id!="+id+" order by id desc";
		String args[] = {"id","name","pwd","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getUsers(String id){
		String sql = "select * from users where id="+id;
		String args[] = {"id","name","pwd","truename","sex","birthday","idcard","phone","address","postalcode","email","status"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getUsers(){
		String sql = "select * from users order by id desc";
		String args[] = {"id","name","pwd","truename","sex","birthday","idcard","phone","address","postalcode","email","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getUsersSearch(String truename){
		String sql = "select * from users where truename like '%"+truename+"%' order by id desc";
		String args[] = {"id","name","pwd","truename","sex","birthday","idcard","phone","address","postalcode","email","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getWard(String id){
		String sql = "select * from ward where id="+id;
		String args[] = {"id","number","offices","grade","price"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getWard(){
		String sql = "select * from ward order by id desc";
		String args[] = {"id","number","offices","grade","price"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getSickbed(String id){
		String sql = "select * from sickbed where id="+id;
		String args[] = {"id","number","ward","states"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getSickbed(){
		String sql = "select * from sickbed order by id desc";
		String args[] = {"id","number","ward","states"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getSickbedWard(String ward){
		String sql = "select * from sickbed where ward="+ward+" order by id desc";
		String args[] = {"id","number","ward","states"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPatient(String id){
		String sql = "select * from patient where id="+id;
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getPatient(){
		String sql = "select * from patient order by id desc";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPatientSearch(String id){
		String sql = "select * from patient where id like '%"+id+"%' order by id desc";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPatientAssign(){
		String sql = "select * from patient where sickbed is null order by id desc";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPatientAssign(String sickbed){
		String sql = "select * from patient where sickbed="+sickbed+" and leavehospital is null";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getPatientWard(String sickbed){
		String sql = "select * from patient where sickbed="+sickbed+" order by id desc";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPatientSymptom(){
		String sql = "select * from patient where leavehospital is null order by id desc";
		String args[] = {"id","pwd","truename","sex","age","birthday","nation","idcard","phone","address","hospitalized","sickbed","leavehospital","status"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getDrug(String id){
		String sql = "select * from drug where id="+id;
		String args[] = {"id","name","norms","approval","factory","ingredient","treat","dosage","price","stock"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getDrug(){
		String sql = "select * from drug order by id desc";
		String args[] = {"id","name","norms","approval","factory","ingredient","treat","dosage","price","stock"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getDrugSearch(String name){
		String sql = "select * from drug where name like '%"+name+"%' order by id desc";
		String args[] = {"id","name","norms","approval","factory","ingredient","treat","dosage","price","stock"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getStock(String id){
		String sql = "select *,datediff(day,validity,getdate()) as fale from stock where id="+id;
		String args[] = {"id","drug","batch","sums","dates","validity","fale"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getStock(){
		String sql = "select *,datediff(day,validity,getdate()) as fale from stock order by id desc";
		String args[] = {"id","drug","batch","sums","dates","validity","fale"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getSymptom(String id){
		String sql = "select * from symptom where id="+id;
		String args[] = {"id","patient","symptom","dates"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getSymptom(){
		String sql = "select * from symptom order by id desc";
		String args[] = {"id","patient","symptom","dates"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPrescribe(String id){
		String sql = "select * from prescribe where id="+id;
		String args[] = {"id","patient","drug","name","sums","price","total","dates"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getPrescribe(){
		String sql = "select * from prescribe order by id desc";
		String args[] = {"id","patient","drug","name","sums","price","total","dates"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getPrescribeFeiyong(String patient){
		String sql = "select * from prescribe where patient='"+patient+"' order by id desc";
		String args[] = {"id","patient","drug","name","sums","price","total","dates"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getInspect(String id){
		String sql = "select * from inspect where id="+id;
		String args[] = {"id","patient","item","price","dates"};
		al = sb.selectRow(args, sql);
		return al;
	}
	
	public ArrayList getInspect(){
		String sql = "select * from inspect order by id desc";
		String args[] = {"id","patient","item","price","dates"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getInspectFeiyong(String patient){
		String sql = "select * from inspect where patient='"+patient+"' order by id desc";
		String args[] = {"id","patient","item","price","dates"};
		al = sb.select(sql, args);
		return al;
	}
	
	public ArrayList getDay(String dates1,String dates2){
		String sql = "select datediff(day,"+dates1+","+dates2+") as days";
		String args[] = {"days"};
		al = sb.selectRow(args, sql);
		return al;
	}
}
