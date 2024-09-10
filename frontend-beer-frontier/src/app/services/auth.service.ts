import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { tap } from "rxjs/operators";
import { environment } from "../../environment/environment";

export class AuthService {
  httpClient = inject(HttpClient);

}