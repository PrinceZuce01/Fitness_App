/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { changePass } from '../fn/user-rest-ipml/change-pass';
import { ChangePass$Params } from '../fn/user-rest-ipml/change-pass';
import { checkToken } from '../fn/user-rest-ipml/check-token';
import { CheckToken$Params } from '../fn/user-rest-ipml/check-token';
import { forgotPass } from '../fn/user-rest-ipml/forgot-pass';
import { ForgotPass$Params } from '../fn/user-rest-ipml/forgot-pass';
import { getAllUser } from '../fn/user-rest-ipml/get-all-user';
import { GetAllUser$Params } from '../fn/user-rest-ipml/get-all-user';
import { login } from '../fn/user-rest-ipml/login';
import { Login$Params } from '../fn/user-rest-ipml/login';
import { signUp } from '../fn/user-rest-ipml/sign-up';
import { SignUp$Params } from '../fn/user-rest-ipml/sign-up';
import { update } from '../fn/user-rest-ipml/update';
import { Update$Params } from '../fn/user-rest-ipml/update';
import { UserWrapper } from '../models/user-wrapper';

@Injectable({ providedIn: 'root' })
export class UserRestIpmlService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `update()` */
  static readonly UpdatePath = '/user/update';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `update()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  update$Response(params: Update$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return update(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `update$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  update(params: Update$Params, context?: HttpContext): Observable<string> {
    return this.update$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `signUp()` */
  static readonly SignUpPath = '/user/signup';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `signUp()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  signUp$Response(params: SignUp$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return signUp(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `signUp$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  signUp(params: SignUp$Params, context?: HttpContext): Observable<string> {
    return this.signUp$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `login()` */
  static readonly LoginPath = '/user/login';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `login()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  login$Response(params: Login$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return login(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `login$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  login(params: Login$Params, context?: HttpContext): Observable<string> {
    return this.login$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `forgotPass()` */
  static readonly ForgotPassPath = '/user/forgotPassword';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `forgotPass()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  forgotPass$Response(params: ForgotPass$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return forgotPass(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `forgotPass$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  forgotPass(params: ForgotPass$Params, context?: HttpContext): Observable<string> {
    return this.forgotPass$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `changePass()` */
  static readonly ChangePassPath = '/user/changePassword';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `changePass()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  changePass$Response(params: ChangePass$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return changePass(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `changePass$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  changePass(params: ChangePass$Params, context?: HttpContext): Observable<string> {
    return this.changePass$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `getAllUser()` */
  static readonly GetAllUserPath = '/user/get';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllUser$Response(params?: GetAllUser$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<UserWrapper>>> {
    return getAllUser(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllUser(params?: GetAllUser$Params, context?: HttpContext): Observable<Array<UserWrapper>> {
    return this.getAllUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<UserWrapper>>): Array<UserWrapper> => r.body)
    );
  }

  /** Path part for operation `checkToken()` */
  static readonly CheckTokenPath = '/user/checkToken';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `checkToken()` instead.
   *
   * This method doesn't expect any request body.
   */
  checkToken$Response(params?: CheckToken$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return checkToken(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `checkToken$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  checkToken(params?: CheckToken$Params, context?: HttpContext): Observable<string> {
    return this.checkToken$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
