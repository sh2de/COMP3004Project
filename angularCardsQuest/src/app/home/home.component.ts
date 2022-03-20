import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../game.service';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  player='';
  private apiServerUrl=environment.apiBaseUrl;
  constructor(private gameService:GameService, private route:Router,private http:HttpClient) { }

  ngOnInit(): void {
    
  }

  join(){
    let url=this.apiServerUrl+"/joinGame"
    console.log(url)
    this.http.post(url,this.player).subscribe(
      (res)=>{
        console.log(res);
        
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
    this.route.navigateByUrl("gameboard/"+this.player);   
    
  }

}
