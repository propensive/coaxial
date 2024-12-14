/*
    Coaxial, version [unreleased]. Copyright 2024 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package coaxial

import anticipation.*
import gossamer.*
import hieroglyph.*
import rudiments.*
import spectacular.*

trait Receivable[+MessageType]:
  def deserialize(message: Bytes): MessageType

object Receivable:
  given bytes: Receivable[Bytes] = identity(_)
  given text(using CharDecoder): Receivable[Text] = _.text
  given decoder[MessageType: Decoder](using CharDecoder): Receivable[MessageType] = _.text.decode[MessageType]
