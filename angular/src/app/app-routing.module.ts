import {NgModule} from "@angular/core";
import { RouterModule, Routes} from "@angular/router";
import {PostListComponent} from "./post-list/post-list.component";
import {PostFormComponent} from "./post-form/post-form.component";


const appRoutes:Routes = [
  {path:'', component:PostListComponent},
  {path:'post/create',component:PostFormComponent}
];
@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule{

}
