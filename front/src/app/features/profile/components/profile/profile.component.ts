import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { map, Observable } from 'rxjs';
import { Topic } from '../../../posts/interfaces/topic.interface';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

constructor(private formBuilder: FormBuilder, private activatedRoute:ActivatedRoute){}

profileForm!: FormGroup;
userSubsribedTopics$!: Observable<Topic[]>


  ngOnInit(): void {
    this.userSubsribedTopics$ = this.activatedRoute.data.pipe(
      map(data => data['userSubscribedTopics'])
    )
    this.initForm();
  }

  private initForm(){
    this.profileForm = this.formBuilder.group({
      username:["", Validators.required],
      email:["", Validators.required],
      password:["", Validators.required]
    })
  }

  public onSubmit(){

  }

  public unSubscribe(topicId:string){}
}
