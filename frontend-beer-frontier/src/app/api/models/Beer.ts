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
import type { Brewery } from './Brewery';
import {
  BreweryFromJSON,
  BreweryFromJSONTyped,
  BreweryToJSON,
} from './Brewery';
import type { Classification } from './Classification';
import {
  ClassificationFromJSON,
  ClassificationFromJSONTyped,
  ClassificationToJSON,
} from './Classification';

/**
 *
 * @export
 * @interface Beer
 */
export interface Beer {
  /**
   *
   * @type {number}
   * @memberof Beer
   */
  id?: number;
  /**
   *
   * @type {string}
   * @memberof Beer
   */
  name?: string;
  /**
   *
   * @type {number}
   * @memberof Beer
   */
  alcoholPercentage?: number;
  /**
   *
   * @type {string}
   * @memberof Beer
   */
  beerType?: BeerBeerTypeEnum;
  /**
   *
   * @type {Brewery}
   * @memberof Beer
   */
  brewery?: Brewery;
  /**
   *
   * @type {Set<Classification>}
   * @memberof Beer
   */
  classifications?: Set<Classification>;
}

/**
 * @export
 */
export const BeerBeerTypeEnum = {
  AlcoholFree: 'ALCOHOL_FREE',
  Ale: 'ALE',
  Fruit: 'FRUIT',
  Cherry: 'CHERRY',
} as const;
export type BeerBeerTypeEnum =
  (typeof BeerBeerTypeEnum)[keyof typeof BeerBeerTypeEnum];

/**
 * Check if a given object implements the Beer interface.
 */
export function instanceOfBeer(value: object): value is Beer {
  return true;
}

export function BeerFromJSON(json: any): Beer {
  return BeerFromJSONTyped(json, false);
}

export function BeerFromJSONTyped(
  json: any,
  ignoreDiscriminator: boolean
): Beer {
  if (json == null) {
    return json;
  }
  return {
    id: json['id'] == null ? undefined : json['id'],
    name: json['name'] == null ? undefined : json['name'],
    alcoholPercentage:
      json['alcoholPercentage'] == null ? undefined : json['alcoholPercentage'],
    beerType: json['beerType'] == null ? undefined : json['beerType'],
    brewery:
      json['brewery'] == null ? undefined : BreweryFromJSON(json['brewery']),
    classifications:
      json['classifications'] == null
        ? undefined
        : new Set(
            (json['classifications'] as Array<any>).map(ClassificationFromJSON)
          ),
  };
}

export function BeerToJSON(value?: Beer | null): any {
  if (value == null) {
    return value;
  }
  return {
    id: value['id'],
    name: value['name'],
    alcoholPercentage: value['alcoholPercentage'],
    beerType: value['beerType'],
    brewery: BreweryToJSON(value['brewery']),
    classifications:
      value['classifications'] == null
        ? undefined
        : Array.from(value['classifications'] as Set<any>).map(
            ClassificationToJSON
          ),
  };
}
