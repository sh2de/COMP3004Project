import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  button='';
  player='';
  constructor() { }

  ngOnInit(): void {
    this.player="player 1";
    this.button='next'
  }

}
