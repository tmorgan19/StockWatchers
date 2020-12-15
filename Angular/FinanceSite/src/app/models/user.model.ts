export class User{
    id:number;
    email:string;
    firstName:string;
    lastName:string;
    password:string;
    username: string;

    constructor(id:number,username:string,password:string,firstName:string,lastName:string,email:string){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email
    }
}