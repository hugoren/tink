// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.google.crypto.tink.apps.paymentmethodtoken;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.nio.charset.StandardCharsets;

/** Various constants. */
class PaymentMethodTokenConstants {
  public static final String GOOGLE_SENDER_ID = "Google";
  public static final String HMAC_SHA256_ALGO = "HmacSha256";
  public static final byte[] HKDF_EMPTY_SALT = new byte[0];
  public static final byte[] GOOGLE_CONTEXT_INFO_ECV1 = "Google".getBytes(StandardCharsets.UTF_8);
  public static final String AES_CTR_ALGO = "AES/CTR/NoPadding";
  // Zero IV is fine here because each encryption uses a unique key.
  public static final byte[] AES_CTR_ZERO_IV = new byte[16];
  public static final EllipticCurves.CurveType P256_CURVE_TYPE =
      EllipticCurves.CurveType.NIST_P256;
  public static final EllipticCurves.PointFormatType UNCOMPRESSED_POINT_FORMAT =
      EllipticCurves.PointFormatType.UNCOMPRESSED;
  public static final String PROTOCOL_VERSION_EC_V1 = "ECv1";
  @Alpha public static final String PROTOCOL_VERSION_EC_V2 = "ECv2";
  public static final String ECDSA_SHA256_SIGNING_ALGO = "SHA256WithECDSA";

  public static final String JSON_ENCRYPTED_MESSAGE_KEY = "encryptedMessage";
  public static final String JSON_EPHEMERAL_PUBLIC_KEY = "ephemeralPublicKey";
  public static final String JSON_INTERMEDIATE_SIGNING_KEY = "intermediateSigningKey";
  public static final String JSON_KEY_EXPIRATION_KEY = "keyExpiration";
  public static final String JSON_KEY_VALUE_KEY = "keyValue";
  public static final String JSON_MESSAGE_EXPIRATION_KEY = "messageExpiration";
  public static final String JSON_PROTOCOL_VERSION_KEY = "protocolVersion";
  public static final String JSON_SIGNATURES_KEY = "signatures";
  public static final String JSON_SIGNATURE_KEY = "signature";
  public static final String JSON_SIGNED_KEY_KEY = "signedKey";
  public static final String JSON_SIGNED_MESSAGE_KEY = "signedMessage";
  public static final String JSON_TAG_KEY = "tag";

  /** Represents configuration regarding each protocol version. */
  enum ProtocolVersionConfig {
    EC_V1(
        /* protocolVersion= */ PROTOCOL_VERSION_EC_V1,
        /* aesCtrKeySize= */ 128 / 8,
        /* hmacSha256KeySize= */ 128 / 8),
    EC_V2(
        /* protocolVersion= */ PROTOCOL_VERSION_EC_V2,
        /* aesCtrKeySize= */ 256 / 8,
        /* hmacSha256KeySize= */ 256 / 8);

    public final String protocolVersion;
    public final int aesCtrKeySize;
    public final int hmacSha256KeySize;

    ProtocolVersionConfig(String protocolVersion, int aesCtrKeySize, int hmacSha256KeySize) {
      this.protocolVersion = protocolVersion;
      this.aesCtrKeySize = aesCtrKeySize;
      this.hmacSha256KeySize = hmacSha256KeySize;
    }

    public static ProtocolVersionConfig forProtocolVersion(String protocolVersion) {
      for (ProtocolVersionConfig protocolVersionConfig : ProtocolVersionConfig.values()) {
        if (protocolVersionConfig.protocolVersion.equals(protocolVersion)) {
          return protocolVersionConfig;
        }
      }
      throw new IllegalArgumentException("Unknown protocol version: " + protocolVersion);
    }
  }
}
