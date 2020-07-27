import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import {  MatFormFieldModule,
  MatInputModule,
  MatGridListModule
  } from '@angular/material';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatGridListModule,
    FlexLayoutModule
  ],
  exports: [
    MatFormFieldModule,
    MatInputModule,
    MatGridListModule,
    FlexLayoutModule
  ]
})
export class SharedModule { }
