import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestUtilService {

  constructor() { }

  public getParams(filters: any[]):HttpParams{
    let params = new HttpParams();
    filters.forEach(filter => {
      var output = filter.replace(/\w+/g, function (txt: string) {
        return txt.charAt(0).toUpperCase() + txt.substr(1);
      }).replace(/\s/g, '');
      params = params.append("filters", output)
    });
    return params;
  }
}
