
import { Component } from "react";
import React from "react";
import Header from "../header";
import FormInput from "./formInputStudent";
// import TestTable from "./testTable";

class AddStudent extends Component{
    render(){
        return(
            <div>
                <Header></Header>
                <FormInput></FormInput>
            </div>
        );
    }
}
export default AddStudent;