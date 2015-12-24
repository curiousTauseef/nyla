function loadFunction()
{
	var table =document.getElementById('t8000q21');
	
	var rows = table.getElementsByTagName("tr");   
	
	var rowLength = rows.length;
	
	//alert(rowLength);	
	    
	    for(i = 0; i < rowLength-2; i++)
	    {       
	       
	       var elementId="v_false_listbox__0_2000_q21[0]["+i+"];tablePK=8000;";
	       
	       var areaId="v_false_listbox__0_2000_q21[1]["+i+"];tablePK=8000;";
	       
	       setOption(this.form,document.getElementById(elementId).selectedIndex,elementId) 
	       
	       var hidElementId="v_false_hidden__0_2000_q21[2]["+i+"];tablePK=8000;";
	       
	       var hidElementValue=document.getElementById(hidElementId).value;
	       
	       //alert(hidElementValue);
	       
	       selectDefaultByElementID(areaId,hidElementValue);
	      
	    }	

}




function setOption(form,index,name) 
{

var nameindex=name.charAt(28);

var listname1=name.substr(0,28);
var listname2=name.substr(29,47);

var nextindex=parseInt(nameindex)+1;

var areas=listname1+nextindex+listname2;

var hidindex=parseInt(nameindex)+1;



//only for the onChange of the listbox for the left column
if(nameindex==0)
{ 	

  if (index == 0) {
  
  	document.getElementById(areas).length=2;
     
  	document.getElementById(areas).options[0].text = "Asthma";
  	document.getElementById(areas).options[0].value = "Asthma";
  	document.getElementById(areas).options[1].text = "Angioedema";
  	document.getElementById(areas).options[1].value = "Angioedema";                                
  }
  
  else if (index == 3) {
  
    document.getElementById(areas).length=10;
  
    document.getElementById(areas).options[0].text   = "Aneurysms";
    document.getElementById(areas).options[0].value  = "Aneurysms";
    document.getElementById(areas).options[1].text = "Angina";
    document.getElementById(areas).options[1].value = "Angina";
    document.getElementById(areas).options[2].text = "Cardiac Arrest";
    document.getElementById(areas).options[2].value = "Cardiac Arrest";
    document.getElementById(areas).options[3].text = "Heart Transplantation";
    document.getElementById(areas).options[3].value = "Heart Transplantation";
    document.getElementById(areas).options[4].text = "Hyperlipidemia";
    document.getElementById(areas).options[4].value = "Hyperlipidemia";
    document.getElementById(areas).options[5].text = "Peripheral Vascular Disease";
    document.getElementById(areas).options[5].value = "Peripheral Vascular Disease";
    document.getElementById(areas).options[6].text = "Hypertension";
    document.getElementById(areas).options[6].value = "Hypertension";
    document.getElementById(areas).options[7].text = "Valvular Heart Disease";
    document.getElementById(areas).options[7].value = "Valvular Heart Disease";
    document.getElementById(areas).options[8].text = "Congestive Heart Failure";
    document.getElementById(areas).options[8].value = "Congestive Heart Failure";
    document.getElementById(areas).options[9].text = "Diagnostic Testing";
    document.getElementById(areas).options[9].value = "Diagnostic Testing";
  }
  else if (index == 7) {
  
    document.getElementById(areas).length=2;
    document.getElementById(areas).options[0].text = "Dental Pain";
    document.getElementById(areas).options[0].value = "Dental Pain";
    document.getElementById(areas).options[1].text = "Dental Surgery";
    document.getElementById(areas).options[1].value = "Dental Surgery";    
  }
  else if (index == 8) {
  
    document.getElementById(areas).length=7;
    
    document.getElementById(areas).options[0].text = "Dermatitis";
    document.getElementById(areas).options[0].value = "Dermatitis";
    document.getElementById(areas).options[1].text = "Dry Skin";
    document.getElementById(areas).options[1].value = "Dry Skin";
    document.getElementById(areas).options[2].text = "Psoriasis";
    document.getElementById(areas).options[2].value = "Psoriasis";
    document.getElementById(areas).options[3].text = "Skin Malignancies";
    document.getElementById(areas).options[3].value = "Skin Malignancies";
    document.getElementById(areas).options[4].text = "HairLoss";
    document.getElementById(areas).options[4].value = "HairLoss";
    document.getElementById(areas).options[5].text = "Hirsutism";
    document.getElementById(areas).options[5].value = "Hirsutism";
    document.getElementById(areas).options[6].text = "HirsutismFemale";
    document.getElementById(areas).options[6].value = "HirsutismFemale";

  }
  
  else if (index == 9) {
  
       document.getElementById(areas).length=7;
      
       document.getElementById(areas).options[0].text = "Diabetes Mellitus";
       document.getElementById(areas).options[0].value = "Diabetes Mellitus";
       document.getElementById(areas).options[1].text = "Diabetic Hyperlipidemia";
       document.getElementById(areas).options[1].value = "Diabetic Hyperlipidemia";
       document.getElementById(areas).options[2].text = "Diabetic Hypertension";
       document.getElementById(areas).options[2].value = "Diabetic Hypertension";
       document.getElementById(areas).options[3].text = "Diabetic Nephropathy";
       document.getElementById(areas).options[3].value = "Diabetic Nephropathy";                        
       document.getElementById(areas).options[4].text = "Diabetic Neuropathy";
       document.getElementById(areas).options[4].value = "Diabetic Neuropathy";
       document.getElementById(areas).options[5].text = "Diabetic Obesity";
       document.getElementById(areas).options[5].value = "Diabetic Obesity";   
       document.getElementById(areas).options[6].text = "Insipidus";
       document.getElementById(areas).options[6].value = "Insipidus";    
                             
    }
  
   else if (index == 10) {
      
      	  document.getElementById(areas).length=1;
      
  	  document.getElementById(areas).options[0].text = "Pediatric";
  	  document.getElementById(areas).options[0].value = "Pediatric";
                  
                                 
    }
    
  
  else if (index == 11) {
  
    document.getElementById(areas).length=4;
    
    document.getElementById(areas).options[0].text = "Hyperthyroidism";
    document.getElementById(areas).options[0].value = "Hyperthyroidism";
    document.getElementById(areas).options[1].text = "Hypothyroidism";
    document.getElementById(areas).options[1].value = "Hypothyroidism";
    document.getElementById(areas).options[2].text = "Thyroid Cancers";
    document.getElementById(areas).options[2].value = "Thyroid Cancers";
    document.getElementById(areas).options[3].text = "Thyroid Disorders";
    document.getElementById(areas).options[3].value = "Thyroid Disorders";
  }
  
   else if (index == 12) {
   
   document.getElementById(areas).length=5;
   
   document.getElementById(areas).options[0].text = "Gastritis";
   document.getElementById(areas).options[0].value = "Gastritis";
   document.getElementById(areas).options[1].text = "Acute & Chronic Hepatitis";
   document.getElementById(areas).options[1].value = "Acute & ChronicHepatitis";      
   document.getElementById(areas).options[2].text = "Colon / Rectal Cancer";
   document.getElementById(areas).options[2].value = "Colon / Rectal Cancer";
   document.getElementById(areas).options[3].text = "GERD";
   document.getElementById(areas).options[3].value = "GERD";    
   document.getElementById(areas).options[4].text = "Hepatology";
   document.getElementById(areas).options[4].value = "Hepatology";   
  }
  
  else if (index == 13) {
  
      document.getElementById(areas).length=2;
      document.getElementById(areas).options[0].text = "Preventative Medicine";
      document.getElementById(areas).options[0].value = "Preventative Medicine";
      document.getElementById(areas).options[1].text = "Nutritional";
      document.getElementById(areas).options[1].value = "Nutritional";      
  }
  
  
  else if (index == 14) {
      
      	    document.getElementById(areas).length=2;
      	    
    	    document.getElementById(areas).options[0].text = "Renal Failure";
    	    document.getElementById(areas).options[0].value = "Renal Failure";
    	    document.getElementById(areas).options[1].text = "Incontinence";
    	    document.getElementById(areas).options[1].value = "Incontinence";      
                                     
      }
      
      else if (index == 15) {
      
      	  document.getElementById(areas).length=6;
      
    	  document.getElementById(areas).options[0].text = "Alzheimer's Disease";
    	  document.getElementById(areas).options[0].value = "Alzheimer's Disease";
    	  document.getElementById(areas).options[1].text = "Bone Densitometry";
    	  document.getElementById(areas).options[1].value = "Bone Densitometry";
    	  document.getElementById(areas).options[2].text = "Dementia";
    	  document.getElementById(areas).options[2].value = "Dementia";
    	  document.getElementById(areas).options[3].text = "Incontinence";
    	  document.getElementById(areas).options[3].value = "Incontinence";
    	  document.getElementById(areas).options[4].text = "Stroke";
    	  document.getElementById(areas).options[4].value = "Stroke";
    	  document.getElementById(areas).options[5].text = "Urology";
    	  document.getElementById(areas).options[5].value = "Urology";                 
          		
                  
        }
      
      else if (index == 16) {
      
      	    document.getElementById(areas).length=4;
      
    	    document.getElementById(areas).options[0].text = "Leukemia";
    	    document.getElementById(areas).options[0].value = "Leukemia";
    	    document.getElementById(areas).options[1].text = "Lymphoma";
    	    document.getElementById(areas).options[1].value = "Lymphoma";
    	    document.getElementById(areas).options[2].text = "Myeloma";
    	    document.getElementById(areas).options[2].value = "Myeloma";
    	    document.getElementById(areas).options[3].text = "Sarcoma";
    	    document.getElementById(areas).options[3].value = "Sarcoma";                           
            		
                    
    }
    
    else if (index == 17) {
    
       document.getElementById(areas).length=11;
            
       document.getElementById(areas).options[0].text = "Adherence";
       document.getElementById(areas).options[0].value = "Adherence";
       document.getElementById(areas).options[1].text = "Drug Resistance";
       document.getElementById(areas).options[1].value = "Drug Resistance";
       document.getElementById(areas).options[2].text = "Gastrointestinal Disorders";
       document.getElementById(areas).options[2].value = "Gastrointestinal Disorders";
       document.getElementById(areas).options[3].text = "Immunology";
       document.getElementById(areas).options[3].value = "Immunology";                        
       document.getElementById(areas).options[4].text = "Kaposi's Sarcoma";
       document.getElementById(areas).options[4].value = "Kaposi's Sarcoma";
       document.getElementById(areas).options[5].text = "Metabolic Disorders";
       document.getElementById(areas).options[5].value = "Metabolic Disorders";   
       document.getElementById(areas).options[6].text = "Neurology";
       document.getElementById(areas).options[6].value = "Neurology";    
       document.getElementById(areas).options[7].text = "Opportunistic Infections";
       document.getElementById(areas).options[7].value = "Opportunistic Infections";
       document.getElementById(areas).options[8].text = "Pediatric";
       document.getElementById(areas).options[8].value = "Pediatric";   
       document.getElementById(areas).options[9].text = "Pharmacokinetics";
       document.getElementById(areas).options[9].value = "Pharmacokinetics";   
       document.getElementById(areas).options[10].text = "Virology";
       document.getElementById(areas).options[10].value = "Virology";    
                                   
    }
  
   else if (index == 19) {
   
  document.getElementById(areas).length=8;
   
  document.getElementById(areas).options[0].text = "Acute & Chronic Hepatitis";
  document.getElementById(areas).options[0].value = "Acute & Chronic Hepatitis";
  document.getElementById(areas).options[1].text = "Lyme Disease";
  document.getElementById(areas).options[1].value = "Lyme Disease";      
  document.getElementById(areas).options[2].text = "STDs";
  document.getElementById(areas).options[2].value = "STDs";
  document.getElementById(areas).options[3].text = "Tuberculosis";
  document.getElementById(areas).options[3].value = "Tuberculosis";    
  document.getElementById(areas).options[4].text = "Hepatology";
  document.getElementById(areas).options[4].value = "Hepatology";             
  document.getElementById(areas).options[5].text = "Meningitis";
  document.getElementById(areas).options[5].value = "Meningitis";
  document.getElementById(areas).options[6].text = "Pediatric";
  document.getElementById(areas).options[6].value = "Pediatric";
  document.getElementById(areas).options[7].text = "Pelvic Infections";
  document.getElementById(areas).options[7].value = "Pelvic Infections";
  }
  
   
      
      
  else if (index == 22) {
  
  	document.getElementById(areas).length=6;
  
	document.getElementById(areas).options[0].text = "Benign Tumors of Bone";
	document.getElementById(areas).options[0].value = "Benign Tumors of Bone";
	document.getElementById(areas).options[1].text = "Osteoarthritis";
	document.getElementById(areas).options[1].value = "Osteoarthritis";
	document.getElementById(areas).options[2].text = "Osteoporosis";
	document.getElementById(areas).options[2].value = "Osteoporosis";
	document.getElementById(areas).options[3].text = "Rheumatoid Arthritis";
	document.getElementById(areas).options[3].value = "Rheumatoid Arthritis";                        
	document.getElementById(areas).options[4].text = "Sciatica";
	document.getElementById(areas).options[4].value = "Sciatica";
	document.getElementById(areas).options[5].text = "Sports Medicine";
	document.getElementById(areas).options[5].value = "Sports Medicine";         		
                     
    }
  
   else if (index == 23) {
   
   	    document.getElementById(areas).length=4;
   
   
            document.getElementById(areas).options[0].text = "Dialysis";
            document.getElementById(areas).options[0].value = "Dialysis";
            document.getElementById(areas).options[1].text = "Filtration Procedures";
            document.getElementById(areas).options[1].value = "Filtration Procedures";      
            document.getElementById(areas).options[2].text = "Nephritic Syndrome";
    	    document.getElementById(areas).options[2].value = "Nephritic Syndrome";
    	    document.getElementById(areas).options[3].text = "Nephrotoxic Disorders";
            document.getElementById(areas).options[3].value = "Nephrotoxic Disorders";    
            
  }
  
  
  else if (index == 24) {
  
  	document.getElementById(areas).length=31;
    
    
      	document.getElementById(areas).options[0].text = "Brain Abscess";
      	document.getElementById(areas).options[0].value = "Brain Abscess";
      	document.getElementById(areas).options[1].text = "Cerebral Vascular Disorders";
      	document.getElementById(areas).options[1].value = "Cerebral Vascular Disorders";      
      	document.getElementById(areas).options[2].text = "Delirium";
      	document.getElementById(areas).options[2].value = "Delirium";
      	document.getElementById(areas).options[3].text = "Dementia";
     	document.getElementById(areas).options[3].value = "Dementia";    
    	document.getElementById(areas).options[4].text = "Neuromuscular Disorders";
      	document.getElementById(areas).options[4].value = "Neuromuscular Disorders";             
      	document.getElementById(areas).options[5].text = "Dystonia";
          document.getElementById(areas).options[5].value = "Dystonia";
          document.getElementById(areas).options[6].text = "Epilepsy";
          document.getElementById(areas).options[6].value = "Epilepsy";
          document.getElementById(areas).options[7].text = "Focal Brain Disorders";
          document.getElementById(areas).options[7].value = "Focal Brain Disorders";
          
          document.getElementById(areas).options[8].text = "Head Trauma";
          document.getElementById(areas).options[8].value = "Head Trauma";             
          document.getElementById(areas).options[9].text = "Headaches & Migraines";
  	document.getElementById(areas).options[9].value = "Headaches & Migraines";
  	document.getElementById(areas).options[10].text = "Huntington's Disease";
  	document.getElementById(areas).options[10].value = "Huntington's Disease";
  	document.getElementById(areas).options[11].text = "Hypersomnia";
          document.getElementById(areas).options[11].value = "Hypersomnia";
          
          
          document.getElementById(areas).options[12].text = "Insomnia";
          document.getElementById(areas).options[12].value = "Insomnia";             
          document.getElementById(areas).options[13].text = "Ischemic Syndromes";
  	document.getElementById(areas).options[13].value = "Ischemic Syndromes";
  	document.getElementById(areas).options[14].text = "Meningitis";
  	document.getElementById(areas).options[14].value = "Meningitis";
  	document.getElementById(areas).options[15].text = "Multiple Sclerosis";
          document.getElementById(areas).options[15].value = "Multiple Sclerosis";
          
          document.getElementById(areas).options[16].text = "Myopathies";
  	document.getElementById(areas).options[16].value = "Myopathies";             
  	document.getElementById(areas).options[17].text = "Muscular Dystrophies";
  	document.getElementById(areas).options[17].value = "Muscular Dystrophies";
  	document.getElementById(areas).options[18].text = "Nerve Root Disorders";
  	document.getElementById(areas).options[18].value = "Nerve Root Disorders";
  	document.getElementById(areas).options[19].text = "Neoplasms";
          document.getElementById(areas).options[19].value = "Neoplasms";
          
          document.getElementById(areas).options[20].text = "Parkinson's Disease";
  	document.getElementById(areas).options[20].value = "Parkinson's Disease";             
  	document.getElementById(areas).options[21].text = "Peripheral Nervous System";
  	document.getElementById(areas).options[21].value = "Peripheral Nervous System";
  	document.getElementById(areas).options[22].text = "Sleep Apnea Syndromes";
  	document.getElementById(areas).options[22].value = "Sleep Apnea Syndromes";
  	document.getElementById(areas).options[23].text = "Spinal Cord Injury";
          document.getElementById(areas).options[23].value = "Spinal Cord Injury";
          
          document.getElementById(areas).options[24].text = "Spinocerebellar Disorders";
  	document.getElementById(areas).options[24].value = "Spinocerebellar Disorders";             
  	document.getElementById(areas).options[25].text = "Stroke";
  	document.getElementById(areas).options[25].value = "Stroke";
  	document.getElementById(areas).options[26].text = "Tremor";
  	document.getElementById(areas).options[26].value = "Tremor";
  	document.getElementById(areas).options[27].text = "Vertigo";
  	document.getElementById(areas).options[27].value = "Vertigo";
  	
  	document.getElementById(areas).options[28].text = "Vision & Eye Movement Disorders";
  	document.getElementById(areas).options[28].value = "Vision & Eye Movement Disorders";             
  	document.getElementById(areas).options[29].text = "Alzheimer's Disease";
  	document.getElementById(areas).options[29].value = "Alzheimer's Disease";
  	document.getElementById(areas).options[30].text = "Sciatica";
  	document.getElementById(areas).options[30].value = "Sciatica";
  	
    }
    
    else if (index == 25) {
    
    	document.getElementById(areas).length=20;
    
      	document.getElementById(areas).options[0].text = "Amenorrhea";
      	document.getElementById(areas).options[0].value = "Amenorrhea";
      	document.getElementById(areas).options[1].text = "Assisted Reproduction";
      	document.getElementById(areas).options[1].value = "Assisted Reproduction";      
      	document.getElementById(areas).options[2].text = "Breast Cancer";
      	document.getElementById(areas).options[2].value = "Breast Cancer";
      	document.getElementById(areas).options[3].text = "Cervical Cancer";
      	document.getElementById(areas).options[3].value = "Cervical Cancer";    
      	document.getElementById(areas).options[4].text = "Endometrial Cancer";
      	document.getElementById(areas).options[4].value = "Endometrial Cancer";             
      	document.getElementById(areas).options[5].text = "Fallopian Tube Cancer";
          document.getElementById(areas).options[5].value = "Fallopian Tube Cancer";
          document.getElementById(areas).options[6].text = "Ovarian Cancer";
          document.getElementById(areas).options[6].value = "Ovarian Cancer";
          document.getElementById(areas).options[7].text = "Vaginal Cancer";
          document.getElementById(areas).options[7].value = "Vaginal Cancer";
          
          
          document.getElementById(areas).options[8].text = "Vulvular Cancer";
  	document.getElementById(areas).options[8].value = "Vulvular Cancer";
  	document.getElementById(areas).options[9].text = "Chronic Anovulvatory Disorders";
  	document.getElementById(areas).options[9].value = "Chronic Anovulvatory Disorders";      
  	document.getElementById(areas).options[10].text = "Contraceptives";
  	document.getElementById(areas).options[10].value = "Contraceptives";
  	document.getElementById(areas).options[11].text = "Hormone Therapy";
  	document.getElementById(areas).options[11].value = "Hormone Therapy";    
  	document.getElementById(areas).options[12].text = "InVitro Fertilization";
  	document.getElementById(areas).options[12].value = "InVitro Fertilization";             
  	document.getElementById(areas).options[13].text = "Infertility";
  	document.getElementById(areas).options[13].value = "Infertility";
  	document.getElementById(areas).options[14].text = "Menopause";
  	document.getElementById(areas).options[14].value = "Menopause";
  	document.getElementById(areas).options[15].text = "Obstetrics";
          document.getElementById(areas).options[15].value = "Obstetrics";
          
          document.getElementById(areas).options[16].text = "Pelvic Infections";
  	document.getElementById(areas).options[16].value = "Pelvic Infections";    
  	document.getElementById(areas).options[17].text = "STDs";
  	document.getElementById(areas).options[17].value = "STDs";             
  	document.getElementById(areas).options[18].text = "Dysmenorrhea";
  	document.getElementById(areas).options[18].value = "Dysmenorrhea";
  	document.getElementById(areas).options[19].text = "Estrogen Replacement";
  	document.getElementById(areas).options[19].value = "Estrogen Replacement";
  	
    }
    
    
    else if (index == 26) {
    
    
    	document.getElementById(areas).length=30;
         
         
    	document.getElementById(areas).options[0].text = "Leukemia";
    	document.getElementById(areas).options[0].value = "Leukemia";
    	document.getElementById(areas).options[1].text = "Lymphoma";
    	document.getElementById(areas).options[1].value = "Lymphoma";
    	document.getElementById(areas).options[2].text = "Head & Neck Cancer";
    	document.getElementById(areas).options[2].value = "Head & Neck Cancer";
    	document.getElementById(areas).options[3].text = "Breast Cancer";
    	document.getElementById(areas).options[3].value = "Breast Cancer";
    	document.getElementById(areas).options[4].text = "Thyroid Cancers";
    	document.getElementById(areas).options[4].value = "Thyroid Cancers";
    	document.getElementById(areas).options[5].text = "Lung Cancer";
    	document.getElementById(areas).options[5].value = "Lung Cancer";
    	document.getElementById(areas).options[6].text = "Esophageal Cancer";
    	document.getElementById(areas).options[6].value = "Esophageal Cancer";
    	document.getElementById(areas).options[7].text = "Stomach Cancer";
    	document.getElementById(areas).options[7].value = "Stomach Cancer";
    	document.getElementById(areas).options[8].text = "Pancreatic Cancer";
    	document.getElementById(areas).options[8].value = "Pancreatic Cancer";
    	document.getElementById(areas).options[9].text = "Liver Cancer";
    	document.getElementById(areas).options[9].value = "Liver Cancer";
    	document.getElementById(areas).options[10].text = "Colon / Rectal Cancer";
    	document.getElementById(areas).options[10].value = "Colon / Rectal Cancer";
    	document.getElementById(areas).options[11].text = "Endometrial Cancer";
    	document.getElementById(areas).options[11].value = "Endometrial Cancer";
    	document.getElementById(areas).options[12].text = "Ovarian Cancer";
    	document.getElementById(areas).options[12].value = "Ovarian Cancer";
    	document.getElementById(areas).options[13].text = "Kidney Cancer";
    	document.getElementById(areas).options[13].value = "Kidney Cancer";
    	document.getElementById(areas).options[14].text = "Prostate Cancer";
    	document.getElementById(areas).options[14].value = "Prostate Cancer";
    
    
    	document.getElementById(areas).options[15].text = "Bladder Cancer";
    	document.getElementById(areas).options[15].value = "Bladder Cancer";
    	document.getElementById(areas).options[16].text = "Testicular Cancer";
    	document.getElementById(areas).options[16].value = "Testicular Cancer";
    	document.getElementById(areas).options[17].text = "Sarcoma / Soft Tissue";
    	document.getElementById(areas).options[17].value = "Sarcoma / Soft Tissue";
    	document.getElementById(areas).options[18].text = "Multiple Myeloma";
    	document.getElementById(areas).options[18].value = "Multiple Myeloma";
    	document.getElementById(areas).options[19].text = "CNS Cancer";
    	document.getElementById(areas).options[19].value = "CNS Cancer";
    	document.getElementById(areas).options[20].text = "Palliative Care";
    	document.getElementById(areas).options[20].value = "Palliative Care";
    	document.getElementById(areas).options[21].text = "Cancer Pain";
    	document.getElementById(areas).options[21].value = "Cancer Pain";
    	document.getElementById(areas).options[22].text = "Cervical Cancer";
    	document.getElementById(areas).options[22].value = "Cervical Cancer";
    	document.getElementById(areas).options[23].text = "Fallopian Tube Cancer";
    	document.getElementById(areas).options[23].value = "Fallopian Tube Cancer";
    	document.getElementById(areas).options[24].text = "Nasopharyngeal Cancer";
    	document.getElementById(areas).options[24].value = "Nasopharyngeal Cancer";
    
    	document.getElementById(areas).options[25].text = "Neoplasms";
    	document.getElementById(areas).options[25].value = "Neoplasms";
    	document.getElementById(areas).options[26].text = "Pediatric";
    	document.getElementById(areas).options[26].value = "Pediatric";
    	document.getElementById(areas).options[27].text = "Skin Malignancies";
    	document.getElementById(areas).options[27].value = "Skin Malignancies";
    	document.getElementById(areas).options[28].text = "Vaginal Cancer";
    	document.getElementById(areas).options[28].value = "Vaginal Cancer";
    	document.getElementById(areas).options[29].text = "Vulvular Cancer";
    	document.getElementById(areas).options[29].value = "Vulvular Cancer";
        		
                
      }
  
 
  
   else if (index == 27) {
   
      document.getElementById(areas).length=2;
   
      document.getElementById(areas).options[0].text = "Glaucoma";
      document.getElementById(areas).options[0].value = "Glaucoma";
      document.getElementById(areas).options[1].text = "Vision & Eye Movement Disorders";
      document.getElementById(areas).options[1].value = "Vision & Eye Movement Disorders";      
                           
  }
  
  else if (index == 29) {
  
       document.getElementById(areas).length=6;
  
       document.getElementById(areas).options[0].text = "Deafness";
       document.getElementById(areas).options[0].value = "Deafness";
       document.getElementById(areas).options[1].text = "Otitis";
       document.getElementById(areas).options[1].value = "Otitis";      
       document.getElementById(areas).options[2].text = "Pharyngitis";
       document.getElementById(areas).options[2].value = "Pharyngitis";
       document.getElementById(areas).options[3].text = "Sinusitis";
       document.getElementById(areas).options[3].value = "Sinusitis";    
       document.getElementById(areas).options[4].text = "Tinnitus";
       document.getElementById(areas).options[4].value = "Tinnitus";             
       document.getElementById(areas).options[5].text = "Tonsillitis ";
       document.getElementById(areas).options[5].value = "Tonsillitis ";
        
  }
  
   else if (index == 32) {
   
   
      document.getElementById(areas).length=11;
      
      document.getElementById(areas).options[0].text = "Critical Care";
      document.getElementById(areas).options[0].value = "Critical Care";
      document.getElementById(areas).options[1].text = "Diagnostic Laboratory Immunology";
      document.getElementById(areas).options[1].value = "Diagnostic Laboratory Immunology";
      document.getElementById(areas).options[2].text = "Emergency Medicine";
      document.getElementById(areas).options[2].value = "Emergency Medicine";
      document.getElementById(areas).options[3].text = "Endocrinology";
      document.getElementById(areas).options[3].value = "Endocrinology";
      document.getElementById(areas).options[4].text = "Gastroenterology";
      document.getElementById(areas).options[4].value = "Gastroenterology";
      document.getElementById(areas).options[5].text = "Hematology / Oncology";
      document.getElementById(areas).options[5].value = "Hematology / Oncology";
      document.getElementById(areas).options[6].text = "Neonatal / Prenatal";
      document.getElementById(areas).options[6].value = "Neonatal / Prenatal";
      document.getElementById(areas).options[7].text = "Cardiology";
      document.getElementById(areas).options[7].value = "Cardiology";
      document.getElementById(areas).options[8].text = "Pulmonary";
      document.getElementById(areas).options[8].value = "Pulmonary";
      document.getElementById(areas).options[9].text = "Rheumatology";
      document.getElementById(areas).options[9].value = "Rheumatology";
      document.getElementById(areas).options[10].text = "Clinical Pharmacology";
      document.getElementById(areas).options[10].value = "Clinical Pharmacology";
  
  }
  
   
  
    
     else if (index == 35) {
     
     	  document.getElementById(areas).length=19;
     
     
          document.getElementById(areas).options[0].text = "Alzheimer's Disease";
          document.getElementById(areas).options[0].value = "Alzheimer's Disease";
          document.getElementById(areas).options[1].text = "Attention Deficit Disorder";
          document.getElementById(areas).options[1].value = "Attention Deficit Disorder";
          document.getElementById(areas).options[2].text = "Bipolar Disorders";
          document.getElementById(areas).options[2].value = "Bipolar Disorders";
          document.getElementById(areas).options[3].text = "Dementia";
          document.getElementById(areas).options[3].value = "Dementia";
          document.getElementById(areas).options[4].text = "Eating Disorders";
          document.getElementById(areas).options[4].value = "Eating Disorders";
          document.getElementById(areas).options[5].text = "Mental Retardation";
          document.getElementById(areas).options[5].value = "Mental Retardation";
          document.getElementById(areas).options[6].text = "Mood Disorders";
          document.getElementById(areas).options[6].value = "Mood Disorders";
          document.getElementById(areas).options[7].text = "Obsessive-Compulsive Disorder";
          document.getElementById(areas).options[7].value = "Obsessive-Compulsive Disorder";
          document.getElementById(areas).options[8].text = "Panic Disorder";
          document.getElementById(areas).options[8].value = "Panic Disorder";
          document.getElementById(areas).options[9].text = "Personality Disorders";
          document.getElementById(areas).options[9].value = "Personality Disorders";
          document.getElementById(areas).options[10].text = "Phobic Neurosis";
          document.getElementById(areas).options[10].value = "Phobic Neurosis";
          document.getElementById(areas).options[11].text = "Post-Traumatic Stress Disorder";
  	document.getElementById(areas).options[11].value = "Post-Traumatic Stress Disorder";
  	document.getElementById(areas).options[12].text = "Schizophrenic Disorders";
  	document.getElementById(areas).options[12].value = "Schizophrenic Disorders";
  	document.getElementById(areas).options[13].text = "Sexual Dysfunction";
  	document.getElementById(areas).options[13].value = "Sexual Dysfunction";
  	document.getElementById(areas).options[14].text = "Sleep Disorders";
  	document.getElementById(areas).options[14].value = "Sleep Disordersq";
  	document.getElementById(areas).options[15].text = "Social Disorders";
  	document.getElementById(areas).options[15].value = "Social Disorders";
  	document.getElementById(areas).options[16].text = "Substance Abuse";
          document.getElementById(areas).options[16].value = "Substance Abuse";
          document.getElementById(areas).options[17].text = "Delirium";
  	document.getElementById(areas).options[17].value = "Delirium";
  	document.getElementById(areas).options[18].text = "Generalized Anxiety Disorder";
          document.getElementById(areas).options[18].value = "Generalized Anxiety Disorder";
      
      }
      
      else if (index == 36) {
      
      	   document.getElementById(areas).length=6;          
          
      	   document.getElementById(areas).options[0].text = "Asthma";
      	   document.getElementById(areas).options[0].value = "Asthma";
      	   document.getElementById(areas).options[1].text = "Bronchitis";
      	   document.getElementById(areas).options[1].value = "Bronchitis";
      	   document.getElementById(areas).options[2].text = "Cystic Fibrosis";
      	   document.getElementById(areas).options[2].value = "Cystic Fibrosis";
      	   document.getElementById(areas).options[3].text = "Pleural Diseases";
      	   document.getElementById(areas).options[3].value = "Pleural Diseases";                        
      	   document.getElementById(areas).options[4].text = "Pneumonia";
      	   document.getElementById(areas).options[4].value = "Pneumonia";
      	   document.getElementById(areas).options[5].text = "Chorea";
      	   document.getElementById(areas).options[5].value = "Chorea";         		
                             
      }
      
       else if (index == 37) {
       
      document.getElementById(areas).length=15;
       
       
      document.getElementById(areas).options[0].text = "Atherosclerosis Imaging";
      document.getElementById(areas).options[0].value = "Atherosclerosis Imaging";
      document.getElementById(areas).options[1].text = "Computed Tomography";
      document.getElementById(areas).options[1].value = "Computed Tomography";
      document.getElementById(areas).options[2].text = "Diagnostic Radiology";
      document.getElementById(areas).options[2].value = "Diagnostic Radiology";
      document.getElementById(areas).options[3].text = "Endocrine System Imaging";
      document.getElementById(areas).options[3].value = "Endocrine System Imaging";
      document.getElementById(areas).options[4].text = "Ischemic Syndrome Imaging";
      document.getElementById(areas).options[4].value = "Ischemic Syndrome Imaging";
      document.getElementById(areas).options[5].text = "Magnetic Resonance Imagine (MRI)";
      document.getElementById(areas).options[5].value = "Magnetic Resonance Imagine (MRI)";
      document.getElementById(areas).options[6].text = "Musculoskeletal Imaging";
      document.getElementById(areas).options[6].value = "Musculoskeletal Imaging";
      document.getElementById(areas).options[7].text = "Psychotic Disorders Imaging";
      document.getElementById(areas).options[7].value = "Psychotic Disorders Imaging";
      document.getElementById(areas).options[8].text = "Radiation Therapy";
      document.getElementById(areas).options[8].value = "Radiation Therapy";
      document.getElementById(areas).options[9].text = "Spinal Canal Imaging";
      document.getElementById(areas).options[9].value = "Spinal Canal Imaging";
      document.getElementById(areas).options[10].text = "Ultrasound Imaging";
      document.getElementById(areas).options[10].value = "Ultrasound Imaging";
      document.getElementById(areas).options[11].text = "Urinary Tract Imaging";
      document.getElementById(areas).options[11].value = "Urinary Tract Imaging";
      document.getElementById(areas).options[12].text = "Fluoroscopy";
      document.getElementById(areas).options[12].value = "Fluoroscopy";
      document.getElementById(areas).options[13].text = "Bone Densitometry";
      document.getElementById(areas).options[13].value = "Bone Densitometry";
      document.getElementById(areas).options[14].text = "Therapeutic Radiology";
      document.getElementById(areas).options[14].value = "Therapeutic Radiology";
  		
          
    }
    
    
    
    else if (index == 38) {
    
    
    	document.getElementById(areas).length=11;
    
    
	document.getElementById(areas).options[0].text = "Laser Surgery";
	document.getElementById(areas).options[0].value = "Laser Surgery";
	document.getElementById(areas).options[1].text = "Neurosurgery";
	document.getElementById(areas).options[1].value = "Neurosurgery";
	document.getElementById(areas).options[2].text = "Orthopedic Surgery";
	document.getElementById(areas).options[2].value = "Orthopedic Surgery";
	document.getElementById(areas).options[3].text = "Pediatric Surgery";
	document.getElementById(areas).options[3].value = "Pediatric Surgery";
	document.getElementById(areas).options[4].text = "Thoracic Surgery";
	document.getElementById(areas).options[4].value = "Thoracic Surgery";
	document.getElementById(areas).options[5].text = "Anesthesiology";
	document.getElementById(areas).options[5].value = "Anesthesiology";
	document.getElementById(areas).options[6].text = "Dental Surgery";
	document.getElementById(areas).options[6].value = "Dental Surgery";
	document.getElementById(areas).options[7].text = "Joint Replacements";
	document.getElementById(areas).options[7].value = "Joint Replacements";
	document.getElementById(areas).options[8].text = "Ostomy Care";
	document.getElementById(areas).options[8].value = "Ostomy Care";
	document.getElementById(areas).options[9].text = "Transplantation";
	document.getElementById(areas).options[9].value = "Transplantation";
	document.getElementById(areas).options[10].text = "Vasculitis";
	document.getElementById(areas).options[10].value = "Vasculitis";     		
            
    }    
  
  
  else if (index == 222) { 
  
  
	      document.getElementById(areas).options[0].text = "Chronic Pain Disorders";
	      document.getElementById(areas).options[0].value = "Chronic Pain Disorders";
	      document.getElementById(areas).options[1].text = "Cancer Pain";
	      document.getElementById(areas).options[1].value = "Cancer Pain";            		
                  
    }  
    
    else
    
    {
    
    	document.getElementById(areas).length=1;    	
    	document.getElementById(areas).options[0].text = document.getElementById(name).options[index].text;
	document.getElementById(areas).options[0].value = document.getElementById(name).options[index].text;
    }
  
  
  
  //setting the default selected index as one stored in the database
  
  var arealength= document.getElementById(areas).options.length;
  
   
  }
  else if(nameindex==1)
  {
  
  
  
  //setting the area of interest selected to hidden variable 
  
  //alert(listname1);
  
  //alert(listname1.substr(0,listname1.indexOf("i")-1));
  
  //alert(listname1.substr(listname1.indexOf("x"),listname1.length));
  
  listname1=listname1.substr(0,listname1.indexOf("i")-1)+'hidden'+listname1.substr(listname1.indexOf("x")+1,listname1.length);
  
  var hidareas=listname1+hidindex+listname2;
  
  //alert(hidareas);
  
  document.getElementById(hidareas).value=document.getElementById(name).options[index].value; 
  
  }
  
}


/*

if(document.getElementById(areas).options[i].value==document.getElementById(hidareas).value)
  	{
  		document.getElementById(areas).options[i].selected=true;
  	}
  	
  	*/