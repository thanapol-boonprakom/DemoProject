import { Component } from 'react';

class Content extends Component{
    render(){
        return(
            <div>
                <p>{this.props.title}</p>
                <p>{this.props.name}</p>
            </div>
        );
    }
}
export default Content;
