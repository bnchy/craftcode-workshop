/* tslint:disable */
/* eslint-disable */
/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import * as runtime from '../runtime';
import type { Classification } from '../models/index';
import { ClassificationFromJSON, ClassificationToJSON } from '../models/index';

export interface GetClassificationByIdRequest {
  id: number;
}

export interface UpdateClassificationRequest {
  id: number;
  classification: Classification;
}

/**
 *
 */
export class ClassificationControllerApi extends runtime.BaseAPI {
  /**
   */
  async getClassificationRaw(
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<runtime.ApiResponse<Array<Classification>>> {
    const queryParameters: any = {};

    const headerParameters: runtime.HTTPHeaders = {};

    const response = await this.request(
      {
        path: `/classifications`,
        method: 'GET',
        headers: headerParameters,
        query: queryParameters,
      },
      initOverrides
    );

    return new runtime.JSONApiResponse(response, jsonValue =>
      jsonValue.map(ClassificationFromJSON)
    );
  }

  /**
   */
  async getClassification(
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<Array<Classification>> {
    const response = await this.getClassificationRaw(initOverrides);
    return await response.value();
  }

  /**
   */
  async getClassificationByIdRaw(
    requestParameters: GetClassificationByIdRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<runtime.ApiResponse<Classification>> {
    if (requestParameters['id'] == null) {
      throw new runtime.RequiredError(
        'id',
        'Required parameter "id" was null or undefined when calling getClassificationById().'
      );
    }

    const queryParameters: any = {};

    const headerParameters: runtime.HTTPHeaders = {};

    const response = await this.request(
      {
        path: `/classifications/{id}`.replace(
          `{${'id'}}`,
          encodeURIComponent(String(requestParameters['id']))
        ),
        method: 'GET',
        headers: headerParameters,
        query: queryParameters,
      },
      initOverrides
    );

    return new runtime.JSONApiResponse(response, jsonValue =>
      ClassificationFromJSON(jsonValue)
    );
  }

  /**
   */
  async getClassificationById(
    requestParameters: GetClassificationByIdRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<Classification> {
    const response = await this.getClassificationByIdRaw(
      requestParameters,
      initOverrides
    );
    return await response.value();
  }

  /**
   */
  async updateClassificationRaw(
    requestParameters: UpdateClassificationRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<runtime.ApiResponse<Classification>> {
    if (requestParameters['id'] == null) {
      throw new runtime.RequiredError(
        'id',
        'Required parameter "id" was null or undefined when calling updateClassification().'
      );
    }

    if (requestParameters['classification'] == null) {
      throw new runtime.RequiredError(
        'classification',
        'Required parameter "classification" was null or undefined when calling updateClassification().'
      );
    }

    const queryParameters: any = {};

    const headerParameters: runtime.HTTPHeaders = {};

    headerParameters['Content-Type'] = 'application/json';

    const response = await this.request(
      {
        path: `/classifications/{id}`.replace(
          `{${'id'}}`,
          encodeURIComponent(String(requestParameters['id']))
        ),
        method: 'PUT',
        headers: headerParameters,
        query: queryParameters,
        body: ClassificationToJSON(requestParameters['classification']),
      },
      initOverrides
    );

    return new runtime.JSONApiResponse(response, jsonValue =>
      ClassificationFromJSON(jsonValue)
    );
  }

  /**
   */
  async updateClassification(
    requestParameters: UpdateClassificationRequest,
    initOverrides?: RequestInit | runtime.InitOverrideFunction
  ): Promise<Classification> {
    const response = await this.updateClassificationRaw(
      requestParameters,
      initOverrides
    );
    return await response.value();
  }
}
