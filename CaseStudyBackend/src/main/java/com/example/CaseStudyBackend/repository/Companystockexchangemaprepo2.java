package com.example.CaseStudyBackend.repository;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.CaseStudyBackend.model.Company;
import com.example.CaseStudyBackend.model.Companystockexchangemap;
import com.example.CaseStudyBackend.model.Stockexchange;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public class Companystockexchangemaprepo2 {
    @Autowired
    CompanyStockexchangemaprepo cstkmaprep;
    @Autowired
    Companyrespository cmprep;
    @Autowired
    StockExchangerepository stkrep;

    @PersistenceContext
    EntityManager entityManager;
    //public Object[] retlist(String cmpname,String exchangename, String compcode ){
    public void retlist(String cmpname,String exchangename, String compcode ){


        Company c = new Company();
        Stockexchange se = new Stockexchange();
//        Query q1 =  entityManager.createQuery(" select c from company c where c.company_name  =:name",Company.class);
//        q1.setParameter("name", cmpname);

//        // System.out.println(cmpname + exchangename +"params in retlist");
//        System.out.println( exchangename +"exchange params in retlist");

        c = cmprep.findByName(cmpname);
//        System.out.println("Look here array" +c.getId());


        // return x;
//
//        Query q2 = entityManager.createQuery(" select c from stock_exchange c where c.name  =:name", Stockexchange.class);
//        q2.setParameter("name", exchangename);

        se = stkrep.findByName(exchangename);
//        System.out.println("Look here array" +c.getClass().getTypeName());

        Companystockexchangemap csmap = new Companystockexchangemap();
        csmap.setCompany(c);
        csmap.setStockexchange(se);
        csmap.setCompanyCode(compcode);
        entityManager.persist(csmap);


    }

    public String readlist(String companyid) {


		/*

		Query q1 = entityManager.createQuery(" select c from Companystockexchangemap csmap where csmap.company  =:companyid", Companystockexchangemap.class);
		q1.setParameter("companyid", companyid);
		Companystockexchangemap cstkmap = new Companystockexchangemap();
		Stockexchange exch = new Stockexchange();

		cstkmap =(Companystockexchangemap) q1.getSingleResult();

		Query q2 = entityManager.createQuery(" select c from Stockexchange exch where exch.id  =:stockexchangeid", Stockexchange.class);
		q2.setParameter("stockexchangeid", cstkmap.getStockexchange());




		exch=(Stockexchange) q2.getSingleResult();



		Query q3 = entityManager.createQuery(" select c from Company cmp where cmp.id  =:companyid", Company.class);
		q3.setParameter("stockexchangeid",  cstkmap.getStockexchange());
		Company cc = new  Company();
		cc=(Company) q3.getSingleResult();


		companycodes = exch.getName() +":"+  cstkmap.getCompanyCode() +":" + cc.getName();
		return companycodes;*/
        String companycodes =" ";
        Query q1 = entityManager.createNativeQuery("select  distinct cmp.name as n1, exch.name as n2  ,cmpexch.Company_Code as n3 from company cmp  , STOCK_EXCHANGE exch , \r\n" +
                "	    			company_stockexchangemap  cmpexch  where cmp.id =:companyid  and  exch.id in \r\n" +
                "	    			(select  a.stockexchange_ID from company_stockexchangemap a  where a.company_id =:companyid)");




        q1.setParameter("companyid", companyid);



        String x = "";


        List<Object[]> results =  q1.getResultList();
        Object[] result = results.get(0);
        System.out.println(results);
        for (Object[] r:results) {
            companycodes = companycodes +"\\r\\n"+ r[0] + "checkout" +r[1] + r[2];
            x ="{\"name\": \""+r[0]+"\", \"exchange\": \""+r[1]+"\",\"Companybcode\":\""+r[2]+"\"}";

            System.out.println(r[0] + "checkout" +r[1] + r[2]);
        }

        return x;

    }

}