<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=windows-1252"%>
<script type="text/javascript" language="JavaScript1.4">
<!--
function createSchedule(frm) {
  var index;
  for(i = 0; i < frm.elements.length; i++) {
    if(frm.elements[i].name == "investigatorId") {
      if(frm.elements[i].checked) {
        index = frm.elements[i].value;
        break;
      }
    }
  }
  var url = '<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>' + "&investigatorId=" + index + "&operation=";

  window.open(url, "newVisitSchedule", "width=280, height=420, resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");
}
-->
</script>
<html:form action="/PSAPanelAction?method=search">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="11" height="5px" align="left" valign="middle">
        <a href="#">&laquo;</a>
      </td>
      <td width="100%">&nbsp;</td>
    </tr>
    <tr>
      <td height="92">&nbsp;</td>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20" colspan="2" valign="top"><h3 class="reportsHeader">Potential Site Assessment</h3></td>
        </tr>
        <tr>
          <td width="48" height="74" valign="top">&nbsp;</td>
          <td width="625" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="109" valign="top" colspan="2"><div align="left" class="displayLabel" style="width:300px; height:26px">Filter Potential Site list by:</div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">Country</div></td>
              <td><div align="left">
                <html:select property="country" style="width:220px" value="USA">
                  <html:option value="USA">United States</html:option>
                  <html:option value="Afghanistan">Afghanistan</html:option>
                  <html:option value="">Albania</html:option>
                  <html:option value="Albania">Algeria</html:option>
                  <html:option value="American Samoa">American Samoa</html:option>
                  <html:option value="Andorra">Andorra</html:option>
                  <html:option value="Anguilla">Anguilla</html:option>
                  <html:option value="Antarctica">Antarctica</html:option>
                  <html:option value="Antigua And Barbuda">Antigua And Barbuda</html:option>
                  <html:option value="Argentina">Argentina</html:option>
                  <html:option value="Armenia">Armenia</html:option>
                  <html:option value="Aruba">Aruba</html:option>
                  <html:option value="Australia">Australia</html:option>
                  <html:option value="Austria">Austria</html:option>
                  <html:option value="Azerbaijan">Azerbaijan</html:option>
                  <html:option value="Bahamas">Bahamas</html:option>
                  <html:option value="Bahrain">Bahrain</html:option>
                  <html:option value="Bangladesh">Bangladesh</html:option>
                  <html:option value="Barbados">Barbados</html:option>
                  <html:option value="Belarus">Belarus</html:option>
                  <html:option value="Belgium">Belgium</html:option>
                  <html:option value="Belize">Belize</html:option>
                  <html:option value="Benin">Benin</html:option>
                  <html:option value="Bermuda">Bermuda</html:option>
                  <html:option value="Bhutan">Bhutan</html:option>
                  <html:option value="Bolivia">Bolivia</html:option>
                  <html:option value="Bosnia and Herzegovina">Bosnia and Herzegovina</html:option>
                  <html:option value="Botswana">Botswana</html:option>
                  <html:option value="Bouvet Island">Bouvet Island</html:option>
                  <html:option value="Brazil">Brazil</html:option>
                  <html:option value="British Indian Ocean Territory">British Indian Ocean Territory</html:option>
                  <html:option value="Brunei Darussalam">Brunei Darussalam</html:option>
                  <html:option value="Bulgaria">Bulgaria</html:option>
                  <html:option value="Burkina Faso">Burkina Faso</html:option>
                  <html:option value="Burundi">Burundi</html:option>
                  <html:option value="Cambodia">Cambodia</html:option>
                  <html:option value="Cameroon">Cameroon</html:option>
                  <html:option value="Canada">Canada</html:option>
                  <html:option value="Cape Verde">Cape Verde</html:option>
                  <html:option value="Cayman Islands">Cayman Islands</html:option>
                  <html:option value="Central African Republic">Central African Republic</html:option>
                  <html:option value="Chad">Chad</html:option>
                  <html:option value="Chile">Chile</html:option>
                  <html:option value="China">China</html:option>
                  <html:option value="Christmas Island">Christmas Island</html:option>
                  <html:option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</html:option>
                  <html:option value="Colombia">Colombia</html:option>
                  <html:option value="Comoros">Comoros</html:option>
                  <html:option value="Congo">Congo</html:option>
                  <html:option value="Congo, the Democratic Republic of the">Congo, the Democratic Republic of the</html:option>
                  <html:option value="Cook Islands">Cook Islands</html:option>
                  <html:option value="Costa Rica">Costa Rica</html:option>
                  <html:option value="Cote d'Ivoire">Cote d'Ivoire</html:option>
                  <html:option value="Cyprus">Cyprus</html:option>
                  <html:option value="Czech Republic">Czech Republic</html:option>
                  <html:option value="Denmark">Denmark</html:option>
                  <html:option value="Djibouti">Djibouti</html:option>
                  <html:option value="Dominica">Dominica</html:option>
                  <html:option value="Dominican Republic">Dominican Republic</html:option>
                  <html:option value="East Timor">East Timor</html:option>
                  <html:option value="Ecuador">Ecuador</html:option>
                  <html:option value="Egypt">Egypt</html:option>
                  <html:option value="El Salvador">El Salvador</html:option>
                  <html:option value="England">England</html:option>
                  <html:option value="Equatorial Guinea">Equatorial Guinea</html:option>
                  <html:option value="Eritrea">Eritrea</html:option>
                  <html:option value="Espana">Espana</html:option>
                  <html:option value="Estonia">Estonia</html:option>
                  <html:option value="Ethiopia">Ethiopia</html:option>
                  <html:option value="Falkland Islands">Falkland Islands</html:option>
                  <html:option value="Faroe Islands">Faroe Islands</html:option>
                  <html:option value="Fiji">Fiji</html:option>
                  <html:option value="Finland">Finland</html:option>
                  <html:option value="France">France</html:option>
                  <html:option value="French Guiana">French Guiana</html:option>
                  <html:option value="French Polynesia">French Polynesia</html:option>
                  <html:option value="French Southern Territories">French Southern Territories</html:option>
                  <html:option value="Gabon">Gabon</html:option>
                  <html:option value="Gambia">Gambia</html:option>
                  <html:option value="Georgia">Georgia</html:option>
                  <html:option value="Germany">Germany</html:option>
                  <html:option value="Ghana">Ghana</html:option>
                  <html:option value="Gibraltar">Gibraltar</html:option>
                  <html:option value="Great Britain">Great Britain</html:option>
                  <html:option value="Greece">Greece</html:option>
                  <html:option value="Greenland">Greenland</html:option>
                  <html:option value="Grenada">Grenada</html:option>
                  <html:option value="Guadeloupe">Guadeloupe</html:option>
                  <html:option value="Guam">Guam</html:option>
                  <html:option value="Guatemala">Guatemala</html:option>
                  <html:option value="Guinea">Guinea</html:option>
                  <html:option value="Guinea-Bissau">Guinea-Bissau</html:option>
                  <html:option value="Guyana">Guyana</html:option>
                  <html:option value="Haiti">Haiti</html:option>
                  <html:option value="Heard and Mc Donald Islands">Heard and Mc Donald Islands</html:option>
                  <html:option value="Honduras">Honduras</html:option>
                  <html:option value="Hong Kong">Hong Kong</html:option>
                  <html:option value="Hungary">Hungary</html:option>
                  <html:option value="Iceland">Iceland</html:option>
                  <html:option value="India">India</html:option>
                  <html:option value="Indonesia">Indonesia</html:option>
                  <html:option value="Ireland">Ireland</html:option>
                  <html:option value="Israel">Israel</html:option>
                  <html:option value="Italy">Italy</html:option>
                  <html:option value="Jamaica">Jamaica</html:option>
                  <html:option value="Japan">Japan</html:option>
                  <html:option value="Jordan">Jordan</html:option>
                  <html:option value="Kazakhstan">Kazakhstan</html:option>
                  <html:option value="Kenya">Kenya</html:option>
                  <html:option value="Kiribati">Kiribati</html:option>
                  <html:option value="Korea, Republic of">Korea, Republic of</html:option>
                  <html:option value="Korea (South)">Korea (South)</html:option>
                  <html:option value="Kuwait">Kuwait</html:option>
                  <html:option value="Kyrgyzstan">Kyrgyzstan</html:option>
                  <html:option value="Lao People's Democratic Republic">Lao People's Democratic Republic</html:option>
                  <html:option value="Latvia">Latvia</html:option>
                  <html:option value="Lebanon">Lebanon</html:option>
                  <html:option value="Lesotho">Lesotho</html:option>
                  <html:option value="Liberia">Liberia</html:option>
                  <html:option value="Libya">Libya</html:option>
                  <html:option value="Liechtenstein">Liechtenstein</html:option>
                  <html:option value="Lithuania">Lithuania</html:option>
                  <html:option value="Luxembourg">Luxembourg</html:option>
                  <html:option value="Macau">Macau</html:option>
                  <html:option value="Macedonia">Macedonia</html:option>
                  <html:option value="Madagascar">Madagascar</html:option>
                  <html:option value="Malawi">Malawi</html:option>
                  <html:option value="Malaysia">Malaysia</html:option>
                  <html:option value="Maldives">Maldives</html:option>
                  <html:option value="Mali">Mali</html:option>
                  <html:option value="Malta">Malta</html:option>
                  <html:option value="Marshall Islands">Marshall Islands</html:option>
                  <html:option value="Martinique">Martinique</html:option>
                  <html:option value="Mauritania">Mauritania</html:option>
                  <html:option value="Mauritius">Mauritius</html:option>
                  <html:option value="Mayotte">Mayotte</html:option>
                  <html:option value="Mexico">Mexico</html:option>
                  <html:option value="Micronesia, Federated States of">Micronesia, Federated States of</html:option>
                  <html:option value="Moldova, Republic of">Moldova, Republic of</html:option>
                  <html:option value="Monaco">Monaco</html:option>
                  <html:option value="Mongolia">Mongolia</html:option>
                  <html:option value="Montserrat">Montserrat</html:option>
                  <html:option value="Morocco">Morocco</html:option>
                  <html:option value="Mozambique">Mozambique</html:option>
                  <html:option value="Myanmar">Myanmar</html:option>
                  <html:option value="Namibia">Namibia</html:option>
                  <html:option value="Nauru">Nauru</html:option>
                  <html:option value="Nepal">Nepal</html:option>
                  <html:option value="Netherlands">Netherlands</html:option>
                  <html:option value="Netherlands Antilles">Netherlands Antilles</html:option>
                  <html:option value="New Caledonia">New Caledonia</html:option>
                  <html:option value="New Zealand">New Zealand</html:option>
                  <html:option value="Nicaragua">Nicaragua</html:option>
                  <html:option value="Niger">Niger</html:option>
                  <html:option value="Nigeria">Nigeria</html:option>
                  <html:option value="Niue">Niue</html:option>
                  <html:option value="Norfolk Island">Norfolk Island</html:option>
                  <html:option value="Northern Ireland">Northern Ireland</html:option>
                  <html:option value="Northern Mariana Islands">Northern Mariana Islands</html:option>
                  <html:option value="Norway">Norway</html:option>
                  <html:option value="Oman">Oman</html:option>
                  <html:option value="Pakistan">Pakistan</html:option>
                  <html:option value="Palau">Palau</html:option>
                  <html:option value="Panama">Panama</html:option>
                  <html:option value="Papua New Guinea">Papua New Guinea</html:option>
                  <html:option value="Paraguay">Paraguay</html:option>
                  <html:option value="Peru">Peru</html:option>
                  <html:option value="Philippines">Philippines</html:option>
                  <html:option value="Pitcairn">Pitcairn</html:option>
                  <html:option value="Poland">Poland</html:option>
                  <html:option value="Portugal">Portugal</html:option>
                  <html:option value="Puerto Rico">Puerto Rico</html:option>
                  <html:option value="Qatar">Qatar</html:option>
                  <html:option value="Reunion">Reunion</html:option>
                  <html:option value="Romania">Romania</html:option>
                  <html:option value="Russia">Russia</html:option>
                  <html:option value="Russian Federation">Russian Federation</html:option>
                  <html:option value="Rwanda">Rwanda</html:option>
                  <html:option value="Saint Kitts and Nevis">Saint Kitts and Nevis</html:option>
                  <html:option value="Saint Lucia">Saint Lucia</html:option>
                  <html:option value="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</html:option>
                  <html:option value="Samoa (Independent">Samoa (Independent)</html:option>
                  <html:option value="San Marino">San Marino</html:option>
                  <html:option value="Sao Tome and Principe">Sao Tome and Principe</html:option>
                  <html:option value="Saudi Arabia">Saudi Arabia</html:option>
                  <html:option value="Scotland">Scotland</html:option>
                  <html:option value="Senegal">Senegal</html:option>
                  <html:option value="Serbia and Montenegro">Serbia and Montenegro</html:option>
                  <html:option value="Seychelles">Seychelles</html:option>
                  <html:option value="Sierra Leone">Sierra Leone</html:option>
                  <html:option value="Singapore">Singapore</html:option>
                  <html:option value="Slovakia">Slovakia</html:option>
                  <html:option value="Slovenia">Slovenia</html:option>
                  <html:option value="Solomon Islands">Solomon Islands</html:option>
                  <html:option value="Somalia">Somalia</html:option>
                  <html:option value="South Africa">South Africa</html:option>
                  <html:option value="South Georgia and the South Sandwich Islands">South Georgia and the South Sandwich Islands</html:option>
                  <html:option value="South Korea">South Korea</html:option>
                  <html:option value="Spain">Spain</html:option>
                  <html:option value="Sri Lanka">Sri Lanka</html:option>
                  <html:option value="St. Helena">St. Helena</html:option>
                  <html:option value="St. Pierre and Miquelon">St. Pierre and Miquelon</html:option>
                  <html:option value="Suriname">Suriname</html:option>
                  <html:option value="Svalbard and Jan Mayen Islands">Svalbard and Jan Mayen Islands</html:option>
                  <html:option value="Swaziland">Swaziland</html:option>
                  <html:option value="Sweden">Sweden</html:option>
                  <html:option value="Switzerland">Switzerland</html:option>
                  <html:option value="Taiwan">Taiwan</html:option>
                  <html:option value="Tajikistan">Tajikistan</html:option>
                  <html:option value="Tanzania">Tanzania</html:option>
                  <html:option value="Thailand">Thailand</html:option>
                  <html:option value="Togo">Togo</html:option>
                  <html:option value="Tokelau">Tokelau</html:option>
                  <html:option value="Tonga">Tonga</html:option>
                  <html:option value="Trinidad">Trinidad</html:option>
                  <html:option value="Trinidad and Tobago">Trinidad and Tobago</html:option>
                  <html:option value="Tunisia">Tunisia</html:option>
                  <html:option value="Turkey">Turkey</html:option>
                  <html:option value="Turkmenistan">Turkmenistan</html:option>
                  <html:option value="Turks and Caicos Islands">Turks and Caicos Islands</html:option>
                  <html:option value="Tuvalu">Tuvalu</html:option>
                  <html:option value="Uganda">Uganda</html:option>
                  <html:option value="Ukraine">Ukraine</html:option>
                  <html:option value="United Arab Emirates">United Arab Emirates</html:option>
                  <html:option value="United Kingdom">United Kingdom</html:option>
                  <html:option value="United States">United States</html:option>
                  <html:option value="United States Minor Outlying Islands">United States Minor Outlying Islands</html:option>
                  <html:option value="Uruguay">Uruguay</html:option>
                  <html:option value="USA">USA</html:option>
                  <html:option value="Uzbekistan">Uzbekistan</html:option>
                  <html:option value="Vanuatu">Vanuatu</html:option>
                  <html:option value="Vatican City">Vatican City State (Holy See)</html:option>
                  <html:option value="Venezuela">Venezuela</html:option>
                  <html:option value="Viet Nam">Viet Nam</html:option>
                  <html:option value="Virgin Islands (British)">Virgin Islands (British)</html:option>
                  <html:option value="Virgin Islands (U.S.)">Virgin Islands (U.S.)</html:option>
                  <html:option value="Wales">Wales</html:option>
                  <html:option value="Wallis and Futuna Islands">Wallis and Futuna Islands</html:option>
                  <html:option value="Western Sahara">Western Sahara</html:option>
                  <html:option value="Yemen">Yemen</html:option>
                  <html:option value="Zambia">Zambia</html:option>
                  <html:option value="Zimbabwe">Zimbabwe</html:option>
                  <html:option value="Croatia">Croatia</html:option>
                </html:select>
              </div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">State</div></td>
              <td><div align="left"><html:text property="state" style="width:220px"/></div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">City</div></td>
              <td><div align="left"><html:text property="city" style="width:220px"/></div></td>
            </tr>
            <tr><td colspan="2">&nbsp;</td></tr>

            <tr>
              <td><div align="left"></div></td>
              <td><div align="left"><html:submit title="Search"/></div></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td height="141">&nbsp;</td>
      <td valign="top" align="center">
        <table width="95%" border="0" cellpadding="0" cellspacing="0" style="border-top:1px solid #000; border-bottom:1px solid #000">
        <tr align="left" valign="top" class="noScroll"  style="border-top:1px solid #000; border-bottom:1px solid #000">
              <td width="2%" class="psiTableHeader" style="height:21px">&nbsp;</td>
              <td width="14%" class="psiTableHeader">Contact</td>
              <td width="1%" class="psiTableHeader">&nbsp;</td>
              <td width="20%" class="psiTableHeader">Address</td>
              <td width="1%" class="psiTableHeader">&nbsp;</td>
              <td width="15%" class="psiTableHeader">City/ST/Province</td>
              <td width="1%" class="psiTableHeader">&nbsp;</td>
              <td width="17%" class="psiTableHeader">Country</td>
              <td width="1%" class="psiTableHeader">&nbsp;</td>
            </tr>
            <tr><td colspan="9">
            <div id="inner_researchSite" style="overflow: auto;width: 100%; height: 100px; border:1px solid #A9A9A9">
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <logic:notEmpty name="psaPanelForm" property="investigatorCollection">
              <nested:iterate name="psaPanelForm" property="investigatorCollection" indexId="index">

                <tr align="left" valign="top" bgcolor="#FFFFFF">
                  <td style="height:21px" width="3%" align="left" valign="top">
                    <input type="radio" name="investigatorId" value='<nested:write property="investigatorId"/>'/>
                  </td>
                  <td width="19%"><nested:write property="lastNm"/>, <nested:write property="firstNm"/></td>
                  <td width="2%">&nbsp;</td>
                  <td width="30%">
                    <nested:write property="address1"/> <nested:write property="address2"/><br />
                    <nested:write property="address3"/> <nested:write property="address4"/>
                  </td>
                  <td width="1%">&nbsp;</td>
                  <td width="22%">
                    <nested:write property="cityNm"/>, <nested:write property="stateProvinceNm"/>
                  </td>
                  <td width="1%">&nbsp;</td>
                  <td width="22%"><nested:write property="countryNm"/></td>
                </tr>
              </nested:iterate>
            </logic:notEmpty>
      </table></div></td></tr></table></td>
    </tr>
    <tr>
      <td height="33" colspan="2" style="padding-left:30px"><html:button property="page" value="Schedule Assessment Visit" title="Schedule Assessment Visit" onclick="createSchedule(this.form);"/></td>
    </tr>
    <tr>
      <td height="33">&nbsp;</td>
      <td valign="top" style="padding-left:15px">Note: PSA Form is pre-populated with data<br />from the selected Site/Investigator</td>
    </tr>
  </table>
</html:form>

