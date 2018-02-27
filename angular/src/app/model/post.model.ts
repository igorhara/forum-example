import {Comment} from "./comment.model";
export class Post{
  id:number;
  title:string;
  content:string;
  owner:string;
  creationDate:Date;
  category:string;
  comments:Comment[];
}
