/************************ Global varibale declarations ************************/
let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
const studentDetails = {};
const regEmail = new RegExp("^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$", 'gy');
const studentId = "SREG00";
const ApplicationId = "APP00";
let count = 0;
let stuId = '';
let appId = '';
const deptObj = {
    "UG": {
        "B.E": ["Construction Technology", "Computer Science", "Genetic Engineering", "Marine Engineering"],
        "B.Tech": ["Aerospace Engineering", "Agricultural Engineering", "Chemical Engineering", "Dairy Technology"]
    }
}

/************************ Ready Function ************************/

$(document).ready(function () {
    const getAppId = document.getElementById("appid");
    const appId = ApplicationId.padEnd(6, "1");
    getAppId.value = appId;

    /************************ API Call for getting last Application ID ************************/
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    let appendpoint = 'http://localhost:8080/getLastApplicationId?Authorization=' + AccessToken;
    $.ajax({
        url: appendpoint,
        type: "get",
        cros: true,
        contentType: "application/text",
        success: function (lastAppId) {
            console.log(lastAppId);
            if (!isNaN(lastAppId)) {
                const newAppId = ApplicationId.padEnd(6, 1);
                document.getElementById("appid").value = newAppId;
            } else {
                const newId = parseInt(lastAppId.substring(5)) + 1;
                console.log(newId);
                const newAppId = ApplicationId.padEnd(6, newId);
                console.log(newAppId);
                document.getElementById("appid").value = newAppId;
            }
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    });

    let Graduate = document.getElementById("graduateid");
    let Department = document.getElementById("departmentid");
    let Course = document.getElementById("courseid");
    for (let x in deptObj) {
        Graduate.options[Graduate.options.length] = new Option(x, x);
        for (let y in deptObj[x]) {
            Department.options[Department.options.length] = new Option(y, y);
            let z = deptObj[x][y];
            for (var i = 0; i < z.length; i++) {
                Course.options[Course.options.length] = new Option(z[i], z[i]);
            }
        }
    }
});

$(document).ready(function () {
    const getRegId = document.getElementById("regid");
    const stuId = studentId.padEnd(7, "1");
    getRegId.value = stuId;

    /************************ API Call for getting last registered student ID ************************/
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    let endpoint = 'http://localhost:8080/getLastStudentRegisterId?Authorization=' + AccessToken;
    $.ajax({
        url: endpoint,
        type: "get",
        cros: true,
        contentType: "application/text",
        success: function (lastRegId) {
            if (!isNaN(lastRegId)) {
                const newStudId = studentId.padEnd(7, 1);
                document.getElementById("regid").value = newStudId;
            } else {
                const newId = parseInt(lastRegId.substring(6)) + 1;
                const newStudId = studentId.padEnd(7, newId);
                document.getElementById("regid").value = newStudId;
            }
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    });

    let Graduate = document.getElementById("graduateid");
    let Department = document.getElementById("departmentid");
    let Course = document.getElementById("courseid");
    for (let x in deptObj) {
        Graduate.options[Graduate.options.length] = new Option(x, x);
        for (let y in deptObj[x]) {
            Department.options[Department.options.length] = new Option(y, y);
            let z = deptObj[x][y];
            for (var i = 0; i < z.length; i++) {
                Course.options[Course.options.length] = new Option(z[i], z[i]);
            }
        }
    }
});

/************************ API call for DashBoard ************************/

$(document).ready(function () {
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    let endpoint = 'http://localhost:8080/totalNoOfStudents?Authorization=' + AccessToken;
    $.ajax({
        url: endpoint,
        type: "get",
      
        contentType: "application/text",
        success: function (totalStudents) {
            if (totalStudents) {
                document.getElementById('no_of_stu').innerHTML = totalStudents;
            } else {

            }
        },
        error: function () {
            alert("Your Token Expired, So please Login...");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    })

    let admission = 'http://localhost:8080/totalNoOfAdmissions?Authorization=' + AccessToken;
    $.ajax({
        url: admission,
        type: "get",
        
        contentType: "application/text",
        success: function (totalAdmission) {
            if (totalAdmission) {
                document.getElementById('no_of_adm').innerHTML = totalAdmission;
            } else {

            }
        }

    })

    let discontinue = 'http://localhost:8080/noOfStudentsDiscontinued?Authorization=' + AccessToken;
    $.ajax({
        url: discontinue,
        type: "get",
        
        contentType: "application/text",
        success: function (totalDiscontinued) {
            if (totalDiscontinued) {
                document.getElementById('no_of_dis').innerHTML = totalDiscontinued;
            } else {

            }
        }
    })
})


/************************ Validate Function for Student Register Form ************************/

function validate(e) {
    count = 0;
    var formflag = true;
    const regId = document.forms.registerForm.reg.value;
    const firstname = document.forms.registerForm.firstname.value;
    const lastname = document.forms.registerForm.lastname.value;
    const date = document.forms.registerForm.date.value;
    const age = document.forms.registerForm.age.value;
    const email = document.forms.registerForm.email.value;
    const mobile = document.forms.registerForm.mobile.value;
    const graduate = document.forms.registerForm.graduate.value;
    const department = document.forms.registerForm.department.value;
    const course = document.forms.registerForm.course.value;
    const cutoff = document.forms.registerForm.cutoff.value;
    const address = document.forms.registerForm.address.value;
    const city = document.forms.registerForm.city.value;
    const pincode = document.forms.registerForm.pincode.value;
    const state = document.forms.registerForm.state.value;
    const country = document.forms.registerForm.country.value;

    if (firstname == "") {
        document.getElementById('error').innerHTML = "Firstname must not be blank!";
        formflag = false;
    }

    if (lastname == "") {
        document.getElementById('error2').innerHTML = "Lastname must not be blank!";
        formflag = false;
    }

    if (date == "") {
        document.getElementById('error1').innerHTML = "Please enter your date!";
        formflag = false;
    }

    if (email == "") {
        document.getElementById('error3').innerHTML = "Email must not be blank!";
        formflag = false;
    }

    if (mobile == "") {
        document.getElementById('error4').innerHTML = "Phone number not be blank!";
        formflag = false;
    }

    if (course == "") {
        document.getElementById('error13').innerHTML = "Please select your desgination!";
        formflag = false;
    }

    if (cutoff == "") {
        document.getElementById('error14').innerHTML = "Please enter your cutoff mark!";
        formflag = false;
    }

    if (address == "") {
        document.getElementById('error9').innerHTML = "Address must not be blank!";
        formflag = false;
    }

    if (city == "") {
        document.getElementById('error5').innerHTML = "City Name must not be blank!";
        formflag = false;
    }

    if (pincode == "") {
        document.getElementById('error6').innerHTML = "Pincode must not be blank!";
        formflag = false;
    }

    if (state == "") {
        document.getElementById('error7').innerHTML = "State name must not be blank!";
        formflag = false;
    }

    if (country == "") {
        document.getElementById('error8').innerHTML = "Country name must not be blank!";
        formflag = false;
    }

    if (formflag) {
        const inputElements = document.querySelectorAll(".form-control");
        for (let i = 1; i < inputElements.length; i++) {
            inputElements[i].value = "";
        }

        /************************ API Call for Inserting Student Records ************************/
        /*api finished*/
        let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
        let endpoint = 'http://localhost:8080/saveApplicationID?registerId=' + regId + '&firstname=' + firstname +
            '&lastname=' + lastname + '&dateOfBirth=' + date + '&age=' + age + '&email=' + email + '&mobilenumber=' + mobile +
            '&graduate=' + graduate + '&department=' + department + '&course=' + course + '&cutoff=' + cutoff + '&address=' + address + '&city=' + city +
            '&pincode=' + pincode + '&state=' + state + '&country=' + country + '&Authorization=' + AccessToken;
        $.ajax({
            url: endpoint,
            type: "post",
            cros: true,
            contentType: "application/text",
            success: function (result) {
                if (result === "Success") {
                    alert("Registered Sucessfully");
                    window.location.reload();
                } else {
                    alert("Invalid Input");
                }
            },
            error: function () {
                //  alert("some error");
                localStorage.removeItem("AccessToken");
                window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

            }
        });
    }
    e.preventDefault();
}

/************************ Error Check Function ************************/

function check(input, span) {
    const spanid = document.getElementById(span);
    const inputid = input.getAttribute('id');
    if (inputid == "firstname") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Firstname must not be blank!");
        }
    }
    else if (inputid == "lastname") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Lastname must not be blank!");
        }
    }
    else if (inputid == "inputEmail4") {
        if (input.value.match(regEmail)) {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Enter your email properly!");
        }
    }
    else if (inputid == "MobileNumber") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Phone number must not be blank!");
        }
    }
    else if (inputid == "courseid") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "This field must not be blank!");
        }
    }
    else if (inputid == "address") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Address must not be blank!");
        }
    }
    else if (inputid == "city") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "City name must not be blank!");
        }
    }
    else if (inputid == "pincode") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Pincode must not be blank!");
        }
    }
    else if (inputid == "state") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "State name must not be blank!");
        }
    }
    else if (inputid == "country") {
        if (input.value != "") {
            spanid.innerHTML = "";
        } else {
            display(spanid, "Country name must not be blank!");
        }
    }
}

function display(span, msg) {
    span.innerHTML = msg;
}

/************************ Validate Function for Admission Funciton ************************/

$('#ad_submit').click(function (e) {
    let registerid = $('#registerid').val();
    let applicationid = $('#appid').val();
    let name = $('#name').val();
    let graduate = $('#graduateid').val();
    let department = $('#departmentid').val();
    let course = $('#courseid').val();
    let username = $('#ad_username').val();
    let password = $('#ad_password').val();
    let usercheck = validateUsername();
    if (usercheck == true) {
        $("#ad_myform").validate({
            rules: {
                register: {
                    required: true,
                },
                ad_password: {
                    required: true,
                    minlength: 3,
                    maxlength: 10
                },
                ad_confirmpassword: {
                    equalTo: "#ad_password"
                }
            },

            messages: {
                password: {
                    minlength: "password should be atleast 3 characters",
                    maxlength: "password should not exceed 3 characters"
                }
            }
        });
    }

    /************************ API Call for Inserting Admission Records ************************/
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    if ($("#ad_myform").valid() && usercheck == true) {
        let endpoint = 'http://localhost:8080/registerUser?registerId=' + registerid + '&applicationId=' + applicationid + '&name=' + name +
            '&graduate=' + graduate + '&department=' + department + '&course=' + course + '&username=' + username + '&password=' + password + '&Authorization=' + AccessToken;
        $.ajax({
            url: endpoint,
            type: "post",
            cros: true,
            contentType: "application/text",
            success: function (result) {
                if (result === "Success") {
                    alert("Registered Sucessfully");
                    window.location.reload();
                } else {
                    alert("Invalid Input");
                }
            },
            error: function () { 
                //  alert("some error");
                localStorage.removeItem("AccessToken");
                window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

            }
        });
    }
    e.preventDefault();
});

/************************ Age Calculator Function ************************/

function ageCalculator(e) {
    var userinput = e.target.value;
    var dob = new Date(userinput);
    var month_diff = Date.now() - dob.getTime();
    var age_dt = new Date(month_diff);
    var year = age_dt.getUTCFullYear();
    var age = Math.abs(year - 1970);
    document.getElementById("ageid").value = age;
    document.getElementById('error1').innerHTML = "";
    if (age >= 18) {
        document.getElementById('error16').innerHTML = "";
    } else {
        document.getElementById('error16').innerHTML = "Age should be above 18";
    }
    document.getElementById('dateid').setAttribute('max', new Date().toISOString().split('T')[0]);
}


/************************ CutOff Function ************************/

function cutOff() {
    let CutOffMark = document.getElementById("cutoffid").value;
    if (CutOffMark >= 150) {
        document.getElementById('error14').innerHTML = "";
    } else {
        document.getElementById('error14').innerHTML = "Cut Off must be above 150!";
    }
}

/************************ Dropdown Select Function ************************/

function select() {
    let Graduate = document.getElementById("graduateid");
    let Department = document.getElementById("departmentid");
    let Course = document.getElementById("courseid");
    Graduate.onchange = function () {
        Department.length = 1;
        Course.length = 1;
        for (let y in deptObj[this.value]) {
            Department.options[Department.options.length] = new Option(y, y);
        }
    }
    Department.onchange = function () {
        Course.length = 1;
        let z = deptObj[Graduate.value][this.value];
        for (var i = 0; i < z.length; i++) {
            Course.options[Course.options.length] = new Option(z[i], z[i]);
        }
    }
}

/************************ Export PDF Function ************************/

function exportApplicationPdf() {
    let endpoint = 'http://localhost:8080/exportAsApplicationPdf'
    $.ajax({
        url: endpoint,
        type: "get",
        cros: true,
        contentType: "apllication/text",
        success: function (result) {
            console.log(result);
            window.open(result)
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    });
}

function exportAdmissionPdf() {
    let endpoint = 'http://localhost:8080/exportAsAdmissionPdf'
    $.ajax({
        url: endpoint,
        type: "get",
        cros: true,
        contentType: "apllication/text",
        success: function (result) {
            console.log(result);
            window.open(result)
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    });
}

/************************ Fetching Student Details Function ************************/

function fetchData(id) {
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    let endpoint = 'http://localhost:8080/ApplicationForm?registerId=' + id + '&Authorization=' + AccessToken;
    $.ajax({
        url: endpoint,
        type: "get",
        datatype: "text",
        cros: true,
        contentType: "application/text",
        success: function (studentDetails) {
            let fname = studentDetails.firstname;
            let sname = studentDetails.lastname;
            let name = fname.concat(" ", sname);
            document.forms.ad_registerForm.name.value = name;
            document.forms.ad_registerForm.graduate.value = studentDetails.graduate;
            document.forms.ad_registerForm.department.value = studentDetails.department;
            document.forms.ad_registerForm.course.value = studentDetails.course;
            document.forms.ad_registerForm.ad_cutoff.value = studentDetails.cutoff;
            document.forms.ad_registerForm.ad_username.value = studentDetails.email;
        },
        //         error: function() {
        //     //  alert("some error");
        //      localStorage.removeItem("AccessToken");
        //                 window.location="http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        //   }
    });
}

/************************ Validate Username Function ************************/

function validateUsername() {
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    let returnType = '';
    const username = document.forms.ad_registerForm.ad_username.value;
    let endpoint = 'http://localhost:8080/checkUserName?username=' + username + '&Authorization=' + AccessToken;
    $.ajax({
        async: false,
        url: endpoint,
        type: "get",
        cros: true,
        contentType: "application/text",
        success: function (userName) {
            if (isNaN(userName) && username === userName) {
                alert("You have already registred!")
                returnType = false;
            } else {
                returnType = true;
            }
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    })
    return returnType;
}

/************************ View Records Function ************************/

function viewRecords() {
    const oldtablebody = document.querySelector('tbody');
    $(oldtablebody).remove();
    const tableid = document.getElementById('tdetails');
    const tbody = document.createElement('tbody');
    for (const obj in studentDetails) {
        const tablerow = document.createElement('tr');
        for (const value in studentDetails[obj]) {
            const td = document.createElement('td');
            td.append(studentDetails[obj][value]);
            tablerow.appendChild(td);
        }
        tbody.appendChild(tablerow);
        tableid.appendChild(tbody);
    }
}

/************************ Remove Record Function ************************/

$('#rr_submit').click(function (e) {
    let registerid = $('#rr_regid').val();
    $("#rr_myform").validate({
        rules: {
            rr_reg: {
                required: true,
            },
            rr_app: {
                required: true,
            },
        },
    });

    // let validator = $("#rr_myform").validate();
    // validator.form();

    if ($("#rr_myform").valid()) {
        let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");

        /************************ API call for removing record ************************/

        let endpoint = 'http://localhost:8080/removingStudent?registerId=' + registerid + '&Authorization=' + AccessToken;
        $.ajax({
            url: endpoint,
            type: "get",
            datatype: "text",
            cros: true,
            contentType: "application/text",
            success: function (result) {
                if (result === "success") {
                    alert("Removed Sucessfully");
                    window.location.reload();
                } else {
                    alert("Invalid Input");
                }
            },
            error: function () {
                //  alert("some error");
                localStorage.removeItem("AccessToken");
                window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

            }
        });
    }
    e.preventDefault();
});

function logout() {
    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    $.ajax({
        url: 'http://localhost:8080/logoutt?Authorization=' + AccessToken,
        type: 'POST',
        contentType: "application/JSON",
        success: function (result) {
            if (result === "Success") {
                localStorage.removeItem("AccessToken");
                window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";
            }
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    })
}

function CurrentUser() {

    let AccessToken = "Manojkumar" + localStorage.getItem("AccessToken");
    $.ajax({
        url: 'http://localhost:8080/GetCurrentUser?Authorization=' + AccessToken,
        type: 'POST',
        contentType: "application/JSON",
        success: function (user) {
            if (result === "Success") {
                localStorage.removeItem("AccessToken");
                window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";
            }
        },
        error: function () {
            //  alert("some error");
            localStorage.removeItem("AccessToken");
            window.location = "http://127.0.0.1:5500/Task%209/screens/CollegeLogin.html";

        }
    })
}



// function AdmissionAddForm(){
//     let AccessToken="Manojkumar"+localStorage.getItem("AccessToken");
//     $.ajax({
//         url: 'http://localhost:8080/AdmissionFormPage?Authorization=' + AccessToken ,
//         type: 'GET',
//         contentType: "application/JSON",
//         success: function (result) {
//                 window.location="http://127.0.0.1:5500/Task%209/screens/admissionForm.html";
//         },
//         error: function() {
//      localStorage.removeItem("AccessToken");
//                 window.location="http://127.0.0.1:5500/Task%209/screens/otpIndex.html";

//   }
//     })
// }

$(document).ready(function myUser() {
    var x = localStorage.getItem("user");
    document.getElementById("demo").innerHTML = x;
  })
