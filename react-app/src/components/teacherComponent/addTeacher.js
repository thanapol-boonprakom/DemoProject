
import { Component } from "react";
import React from "react";
import Header from "../header";
import FormInput from "./formInputAddTeacher";
// import TestTable from "./testTable";

class AddTeacher extends Component{
    render(){
        return(
            <div>
                <Header></Header>
                <FormInput></FormInput>
            </div>
        );
    }
}
export default AddTeacher;


