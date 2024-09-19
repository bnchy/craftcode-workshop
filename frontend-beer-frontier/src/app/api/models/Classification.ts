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

import { mapValues } from '../runtime';
import type { Beer } from './Beer';
import {
    BeerFromJSON,
    BeerFromJSONTyped,
    BeerToJSON,
} from './Beer';

/**
 * 
 * @export
 * @interface Classification
 */
export interface Classification {
    /**
     * 
     * @type {number}
     * @memberof Classification
     */
    id?: number;
    /**
     * 
     * @type {string}
     * @memberof Classification
     */
    country?: ClassificationCountryEnum;
    /**
     * 
     * @type {string}
     * @memberof Classification
     */
    usedGrainType?: ClassificationUsedGrainTypeEnum;
    /**
     * 
     * @type {string}
     * @memberof Classification
     */
    fermentationType?: ClassificationFermentationTypeEnum;
    /**
     * 
     * @type {string}
     * @memberof Classification
     */
    namesAndOrigins?: ClassificationNamesAndOriginsEnum;
    /**
     * 
     * @type {Set<Beer>}
     * @memberof Classification
     */
    beers?: Set<Beer>;
}


/**
 * @export
 */
export const ClassificationCountryEnum = {
    Belgium: 'BELGIUM',
    Germany: 'GERMANY',
    America: 'AMERICA',
    Spain: 'SPAIN',
    France: 'FRANCE'
} as const;
export type ClassificationCountryEnum = typeof ClassificationCountryEnum[keyof typeof ClassificationCountryEnum];

/**
 * @export
 */
export const ClassificationUsedGrainTypeEnum = {
    Barely: 'BARELY',
    Wheat: 'WHEAT',
    Corn: 'CORN',
    BaseMalt: 'BASE_MALT',
    Oats: 'OATS'
} as const;
export type ClassificationUsedGrainTypeEnum = typeof ClassificationUsedGrainTypeEnum[keyof typeof ClassificationUsedGrainTypeEnum];

/**
 * @export
 */
export const ClassificationFermentationTypeEnum = {
    Warm: 'WARM',
    Cool: 'COOL',
    Wild: 'WILD',
    Spontaneous: 'SPONTANEOUS'
} as const;
export type ClassificationFermentationTypeEnum = typeof ClassificationFermentationTypeEnum[keyof typeof ClassificationFermentationTypeEnum];

/**
 * @export
 */
export const ClassificationNamesAndOriginsEnum = {
    AbbeyBeer: 'ABBEY_BEER',
    TrappistBeer: 'TRAPPIST_BEER',
    DarkBeer: 'DARK_BEER'
} as const;
export type ClassificationNamesAndOriginsEnum = typeof ClassificationNamesAndOriginsEnum[keyof typeof ClassificationNamesAndOriginsEnum];


/**
 * Check if a given object implements the Classification interface.
 */
export function instanceOfClassification(value: object): value is Classification {
    return true;
}

export function ClassificationFromJSON(json: any): Classification {
    return ClassificationFromJSONTyped(json, false);
}

export function ClassificationFromJSONTyped(json: any, ignoreDiscriminator: boolean): Classification {
    if (json == null) {
        return json;
    }
    return {
        
        'id': json['id'] == null ? undefined : json['id'],
        'country': json['country'] == null ? undefined : json['country'],
        'usedGrainType': json['usedGrainType'] == null ? undefined : json['usedGrainType'],
        'fermentationType': json['fermentationType'] == null ? undefined : json['fermentationType'],
        'namesAndOrigins': json['namesAndOrigins'] == null ? undefined : json['namesAndOrigins'],
        'beers': json['beers'] == null ? undefined : (new Set((json['beers'] as Array<any>).map(BeerFromJSON))),
    };
}

export function ClassificationToJSON(value?: Classification | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'id': value['id'],
        'country': value['country'],
        'usedGrainType': value['usedGrainType'],
        'fermentationType': value['fermentationType'],
        'namesAndOrigins': value['namesAndOrigins'],
        'beers': value['beers'] == null ? undefined : (Array.from(value['beers'] as Set<any>).map(BeerToJSON)),
    };
}

