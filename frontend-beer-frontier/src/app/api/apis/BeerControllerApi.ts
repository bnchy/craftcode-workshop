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
import type {
  Beer,
  PageBeer,
} from '../models/index';
import {
    BeerFromJSON,
    BeerToJSON,
    PageBeerFromJSON,
    PageBeerToJSON,
} from '../models/index';

export interface AddBeerRequest {
    beer: Beer;
}

export interface DeleteBeerRequest {
    id: number;
}

export interface GetBeerRequest {
    id: number;
}

export interface GetBeersRequest {
    pageNr: number;
    pageSize: number;
}

export interface GetBeersByBreweryIdRequest {
    breweryId: number;
}

export interface SearchBeersRequest {
    searchTerm: string;
    pageNr: number;
    pageSize: number;
}

export interface UpdateBeerRequest {
    id: number;
    beer: Beer;
}

/**
 * 
 */
export class BeerControllerApi extends runtime.BaseAPI {

    /**
     */
    async addBeerRaw(requestParameters: AddBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Beer>> {
        if (requestParameters['beer'] == null) {
            throw new runtime.RequiredError(
                'beer',
                'Required parameter "beer" was null or undefined when calling addBeer().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/beers`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: BeerToJSON(requestParameters['beer']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => BeerFromJSON(jsonValue));
    }

    /**
     */
    async addBeer(requestParameters: AddBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Beer> {
        const response = await this.addBeerRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async deleteBeerRaw(requestParameters: DeleteBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling deleteBeer().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/beers/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'DELETE',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     */
    async deleteBeer(requestParameters: DeleteBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.deleteBeerRaw(requestParameters, initOverrides);
    }

    /**
     */
    async getBeerRaw(requestParameters: GetBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Beer>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling getBeer().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/beers/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => BeerFromJSON(jsonValue));
    }

    /**
     */
    async getBeer(requestParameters: GetBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Beer> {
        const response = await this.getBeerRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async getBeersRaw(requestParameters: GetBeersRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<PageBeer>> {
        if (requestParameters['pageNr'] == null) {
            throw new runtime.RequiredError(
                'pageNr',
                'Required parameter "pageNr" was null or undefined when calling getBeers().'
            );
        }

        if (requestParameters['pageSize'] == null) {
            throw new runtime.RequiredError(
                'pageSize',
                'Required parameter "pageSize" was null or undefined when calling getBeers().'
            );
        }

        const queryParameters: any = {};

        if (requestParameters['pageNr'] != null) {
            queryParameters['pageNr'] = requestParameters['pageNr'];
        }

        if (requestParameters['pageSize'] != null) {
            queryParameters['pageSize'] = requestParameters['pageSize'];
        }

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/beers`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => PageBeerFromJSON(jsonValue));
    }

    /**
     */
    async getBeers(requestParameters: GetBeersRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<PageBeer> {
        const response = await this.getBeersRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async getBeersByBreweryIdRaw(requestParameters: GetBeersByBreweryIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<Beer>>> {
        if (requestParameters['breweryId'] == null) {
            throw new runtime.RequiredError(
                'breweryId',
                'Required parameter "breweryId" was null or undefined when calling getBeersByBreweryId().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/beers/by-brewery/{breweryId}`.replace(`{${"breweryId"}}`, encodeURIComponent(String(requestParameters['breweryId']))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(BeerFromJSON));
    }

    /**
     */
    async getBeersByBreweryId(requestParameters: GetBeersByBreweryIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<Beer>> {
        const response = await this.getBeersByBreweryIdRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async searchBeersRaw(requestParameters: SearchBeersRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<PageBeer>> {
        if (requestParameters['searchTerm'] == null) {
            throw new runtime.RequiredError(
                'searchTerm',
                'Required parameter "searchTerm" was null or undefined when calling searchBeers().'
            );
        }

        if (requestParameters['pageNr'] == null) {
            throw new runtime.RequiredError(
                'pageNr',
                'Required parameter "pageNr" was null or undefined when calling searchBeers().'
            );
        }

        if (requestParameters['pageSize'] == null) {
            throw new runtime.RequiredError(
                'pageSize',
                'Required parameter "pageSize" was null or undefined when calling searchBeers().'
            );
        }

        const queryParameters: any = {};

        if (requestParameters['searchTerm'] != null) {
            queryParameters['searchTerm'] = requestParameters['searchTerm'];
        }

        if (requestParameters['pageNr'] != null) {
            queryParameters['pageNr'] = requestParameters['pageNr'];
        }

        if (requestParameters['pageSize'] != null) {
            queryParameters['pageSize'] = requestParameters['pageSize'];
        }

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/beers/search`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => PageBeerFromJSON(jsonValue));
    }

    /**
     */
    async searchBeers(requestParameters: SearchBeersRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<PageBeer> {
        const response = await this.searchBeersRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async updateBeerRaw(requestParameters: UpdateBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Beer>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling updateBeer().'
            );
        }

        if (requestParameters['beer'] == null) {
            throw new runtime.RequiredError(
                'beer',
                'Required parameter "beer" was null or undefined when calling updateBeer().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/beers/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: BeerToJSON(requestParameters['beer']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => BeerFromJSON(jsonValue));
    }

    /**
     */
    async updateBeer(requestParameters: UpdateBeerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Beer> {
        const response = await this.updateBeerRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
