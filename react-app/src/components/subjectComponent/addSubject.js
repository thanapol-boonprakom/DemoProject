
import { Component } from "react";
import React from "react";
import Header from "../header";
import FormInput from "./formInputAddSubject";
// import TestTable from "./testTable";

class AddSubject extends Component{
    render(){
        return(
            <div>
                <Header></Header>
                <FormInput></FormInput>
            </div>
        );
    }
}
export default AddSubject;